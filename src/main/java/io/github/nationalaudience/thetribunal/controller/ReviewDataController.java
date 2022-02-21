package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.entity.Game;
import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.Studio;
import io.github.nationalaudience.thetribunal.repository.GameRepository;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import io.github.nationalaudience.thetribunal.repository.ReviewRepository;
import io.github.nationalaudience.thetribunal.repository.StudioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

import static io.github.nationalaudience.thetribunal.constant.GameDataStaticValues.ATTRIBUTE_GAME_NAME;
import static io.github.nationalaudience.thetribunal.constant.GenericDataStaticValues.ATTRIBUTE_DATA;
import static io.github.nationalaudience.thetribunal.constant.GenericDataStaticValues.ATTRIBUTE_TYPE;
import static io.github.nationalaudience.thetribunal.constant.ReviewsStaticValues.*;

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
        //model.addAttribute(PARAMETER_GAME_NAME, gameName);
        session.setAttribute(PARAMETER_GAME_NAME, gameName);
        System.out.println(gameName);
        return TEMPLATE_NEW_REVIEW;
    }

    @PostMapping(END_POINT_POST_NEW_REVIEW_TO_DB)
    public String postNewReviewToDb(
            Model model,
            HttpSession session,
            @RequestParam(PARAMETER_USER_NAME) String userName,
            @RequestParam(PARAMETER_COMMENT) String comment,
            @RequestParam(PARAMETER_SCORE) String score) {

        var aux = session.getAttribute(PARAMETER_GAME_NAME);
        var gameName = aux == null ? null : aux.toString();
        if (userName.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Username field cannot be empty!");
            return TEMPLATE_NEW_REVIEW;
        }

        var exist_user = userRepository.findByUsername(userName);
        if (!exist_user.isPresent()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "User " + userName + " does not exists!");
            return TEMPLATE_NEW_REVIEW;
        }

        if (gameName == null) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Game name field cannot be empty!");
            return TEMPLATE_NEW_REVIEW;
        }

        var exist_game = gameRepository.findByName(gameName);
        if (!exist_game.isPresent()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Game " + gameName + " does not exists!");
            return TEMPLATE_NEW_REVIEW;
        }

        if (comment.isEmpty() || comment.length() < 50) {
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
                exist_user.get(),
                exist_game.get()
        );

        reviewRepository.save(newReview);

        model.addAttribute("game", exist_game.get());
        model.addAttribute(ATTRIBUTE_TYPE, "game");
        model.addAttribute(ATTRIBUTE_DATA, gameName);
        return TEMPLATE_POST_NEW_REVIEW;
    }
}
