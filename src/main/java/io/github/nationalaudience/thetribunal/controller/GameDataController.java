package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.repository.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static io.github.nationalaudience.thetribunal.constant.GameDataStaticValues.*;

@Controller
public class GameDataController {

    private final GameRepository gameRepository;

    public GameDataController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping(END_POINT_GAME_DATA)
    public String userData(Model model, @PathVariable(PARAMETER_GAME) String inName) {
        var optional = gameRepository.findByName(inName);

        if (optional.isPresent()) {
            var game = optional.get();
            model.addAttribute(ATTRIBUTE_GAME_NAME, game);
            return TEMPLATE_GAME_DATA;
        } else {
            return TEMPLATE_GAME_DATA_NOT_FOUND;
        }
    }
}
