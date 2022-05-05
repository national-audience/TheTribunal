package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.constant.GameDataStaticValues;
import io.github.nationalaudience.thetribunal.constant.LoginStaticValues;
import io.github.nationalaudience.thetribunal.constant.UserDataStaticValues;
import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.repository.GameRepository;
import io.github.nationalaudience.thetribunal.repository.ReviewRepository;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static io.github.nationalaudience.thetribunal.constant.GenericDataStaticValues.*;
import static io.github.nationalaudience.thetribunal.constant.ReviewsStaticValues.*;
import static io.github.nationalaudience.thetribunal.constant.UserDataStaticValues.*;

@Controller
public class ReviewDataController {

    private final ReviewRepository reviewRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public ReviewDataController(ReviewRepository reviewRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(END_POINT_NEW_REVIEW_TO_DB)
    public String newReviewToDb(Model model, HttpSession session, @RequestParam(PARAMETER_GAME_NAME) String gameName) {
        session.setAttribute(PARAMETER_GAME_NAME, gameName);
        return TEMPLATE_NEW_REVIEW;
    }

    @PostMapping(END_POINT_POST_NEW_REVIEW_TO_DB)
    @CacheEvict(value="feed", allEntries = true)
    public String postNewReviewToDb(
            Model model,
            HttpSession session,
            @RequestParam(PARAMETER_COMMENT) String comment,
            @RequestParam(PARAMETER_SCORE) String score) {

        var aux = session.getAttribute(PARAMETER_GAME_NAME);
        var gameName = aux == null ? null : aux.toString();

        var user = (User) model.getAttribute(LoginStaticValues.CACHE_LOGGED_USER);
        if (user == null) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "You're not logged in!");
            return TEMPLATE_NEW_REVIEW;
        }

        if (gameName == null) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Game name field cannot be empty!");
            return TEMPLATE_NEW_REVIEW;
        }

        var exist_game = gameRepository.findByName(gameName);
        if (exist_game.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Game " + gameName + " does not exists!");
            return TEMPLATE_NEW_REVIEW;
        }

        if (comment.length() < 50) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Comment field cannot be under 50 characters!");
            return TEMPLATE_NEW_REVIEW;
        }

        if (score.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Score can not be empty!");
            return TEMPLATE_NEW_REVIEW;
        }

        int score_int = Integer.parseInt(score);
        if (score_int < 0 || score_int > 10) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Score invalid! (Only 0-10)");
            return TEMPLATE_NEW_REVIEW;
        }

        var newReview = new Review(
                score_int,
                comment,
                new Date(),
                user,
                exist_game.get()
        );

        reviewRepository.save(newReview);

        model.addAttribute(ATTRIBUTE_GAME, exist_game.get());
        model.addAttribute(ATTRIBUTE_TYPE, "game");
        model.addAttribute(ATTRIBUTE_DATA, gameName);
        return TEMPLATE_POST_NEW_REVIEW;
    }

    @PostMapping(END_POINT_DELETE_REVIEW_TO_DB)
    @CacheEvict(value="feed", allEntries = true)
    public String deleteReview(
            Model model,
            @RequestParam(PARAMETER_USER_NAME) String userName,
            @RequestParam(PARAMETER_GAME_NAME) String gameName,
            @RequestParam(PARAMETER_DELETE_RETURN_GAME) boolean returnToGameData
    ) {
        var loggedUser = (User) model.getAttribute(LoginStaticValues.CACHE_LOGGED_USER);
        var game = gameRepository.findByName(gameName).orElseThrow();
        var user = userRepository.findByUsername(userName).orElseThrow();

        if (loggedUser != null && (loggedUser.isAdmin() || loggedUser.getUsername().equals(userName))) {
            reviewRepository.findByUserAndGame(user, game).ifPresent(reviewRepository::delete);
        }

        if (returnToGameData) {
            model.addAttribute(ATTRIBUTE_GAME, game);
            model.addAttribute(ATTRIBUTE_TYPE, "game");
            model.addAttribute(ATTRIBUTE_DATA, gameName);
            return GameDataStaticValues.TEMPLATE_GAME_DATA;
        } else {
            String count_followers = String.valueOf(user.getFollowedByUsers().size());
            String count_follows = String.valueOf(user.getUsersFollow().size());
            String count_studio_follows = String.valueOf(user.getStudiosFollow().size());

            model.addAttribute(ATTRIBUTE_USER, user);
            model.addAttribute(ATTRIBUTE_USER_FOLLOWERS, count_followers);
            model.addAttribute(ATTRIBUTE_USER_FOLLOWS, count_follows);
            model.addAttribute(ATTRIBUTE_USER_STUDIO_FOLLOWS, count_studio_follows);
            model.addAttribute(ATTRIBUTE_DATA, userName);
            model.addAttribute(ATTRIBUTE_TYPE, "user");
            return UserDataStaticValues.TEMPLATE_USER_DATA;
        }
    }
}
