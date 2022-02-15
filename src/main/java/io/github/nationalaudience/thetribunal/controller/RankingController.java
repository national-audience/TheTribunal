package io.github.nationalaudience.thetribunal.controller;

import static io.github.nationalaudience.thetribunal.constant.RankingStaticValues.*;

import static io.github.nationalaudience.thetribunal.constant.SearchStaticValues.ATTRIBUTE_HAS_USERS;

import io.github.nationalaudience.thetribunal.repository.GameRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Controller
public class RankingController {

    private final GameRepository gameRepository;

    public RankingController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping(END_POINT_RANKING)
    public String showRanking(Model model) {

        var averageScores = gameRepository.getAllAverageScores();

        model.addAttribute("scores", averageScores);

        return RANKING_TEMPLATE;
    }
}
