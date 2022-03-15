package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.constant.LoginStaticValues;
import io.github.nationalaudience.thetribunal.entity.Game;
import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.repository.GameRepository;
import io.github.nationalaudience.thetribunal.repository.StudioRepository;
import io.github.nationalaudience.thetribunal.util.ReviewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.github.nationalaudience.thetribunal.constant.GameDataStaticValues.*;
import static io.github.nationalaudience.thetribunal.constant.GenericDataStaticValues.*;

@Controller
public class GameDataController {

    private final GameRepository gameRepository;
    private final StudioRepository studioRepository;

    public GameDataController(GameRepository gameRepository, StudioRepository studioRepository) {
        this.gameRepository = gameRepository;
        this.studioRepository = studioRepository;
    }

    @GetMapping(END_POINT_GAME_DATA)
    public String gameData(Model model, @PathVariable(PARAMETER_GAME) String inName) {
        var optional = gameRepository.findByName(inName);

        if (optional.isPresent()) {
            var loggedUser = (User) model.getAttribute(LoginStaticValues.CACHE_LOGGED_USER);

            var game = optional.get();
            model.addAttribute(ATTRIBUTE_GAME_NAME, game);
            model.addAttribute(ATTRIBUTE_GAME_REVIEWS, ReviewUtils.makeReviewSnapshot(game.getReviews(), loggedUser));
            model.addAttribute(ATTRIBUTE_TYPE, "game");
            model.addAttribute(ATTRIBUTE_DATA, inName);
            return TEMPLATE_GAME_DATA;
        } else {
            return TEMPLATE_GAME_DATA_NOT_FOUND;
        }
    }

    @GetMapping(END_POINT_NEW_GAME_TO_DB)
    public String newGameToDb(Model model) {
        return TEMPLATE_NEW_GAME_TO_DB;
    }

    @PostMapping(END_POINT_POST_NEW_GAME_TO_DB)
    public String postNewGameToDb(
            Model model,
            @RequestParam(PARAMETER_GAME_NAME) String name,
            @RequestParam(PARAMETER_GAME_STUDIO) String studioName,
            @RequestParam(PARAMETER_GAME_DESCRIPTION) String description,
            @RequestParam(PARAMETER_DATE_DESCRIPTION) String date) throws ParseException {

        model.addAttribute(PARAMETER_GAME_NAME, name);

        if (name.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Game name field cannot be empty!");
            return TEMPLATE_NEW_GAME_TO_DB;
        }

        if (studioName.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Studio name field cannot be empty!");
            return TEMPLATE_NEW_GAME_TO_DB;
        }

        if (description.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Description field cannot be empty!");
            return TEMPLATE_NEW_GAME_TO_DB;
        }

        if (date.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Release date field cannot be empty!");
            return TEMPLATE_NEW_GAME_TO_DB;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateD = formatter.parse(date);

        var game = gameRepository.findByName(name);
        if (game.isPresent()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "The game "
                    + game.get().getName()
                    + " is already registered!");
            return TEMPLATE_NEW_GAME_TO_DB;
        }

        var studio = studioRepository.findByName(studioName);
        if (!studio.isPresent()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "The studio "
                    + studioName
                    + " is not registered!");
            return TEMPLATE_NEW_GAME_TO_DB;
        }

        var newGame = new Game(
                name,
                description,
                List.of(studio.get()),
                dateD,
                new ArrayList<>()
        );

        gameRepository.save(newGame);

        model.addAttribute(ATTRIBUTE_GAME_NAME, name);
        return TEMPLATE_POST_NEW_GAME_TO_DB;
    }

    @PostMapping(END_POINT_DELETE_GAME_DATA + "/{inName}")
    public String deleteStudioData(Model model, @PathVariable(PARAMETER_GAME) String inName) {

        var game = gameRepository.findByName(inName);

        game.ifPresent(gameRepository::delete);

        model.addAttribute(ATTRIBUTE_TYPE, "game");
        model.addAttribute(ATTRIBUTE_DATA, inName);

        return TEMPLATE_DATA_DELETED;
    }
}
