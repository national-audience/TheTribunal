package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.repository.GameRepository;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static io.github.nationalaudience.thetribunal.constant.RankingStaticValues.END_POINT_RANKING;
import static io.github.nationalaudience.thetribunal.constant.RankingStaticValues.RANKING_TEMPLATE;
import static io.github.nationalaudience.thetribunal.constant.SearchStaticValues.PARAMETER_SEARCH_QUERY;
import static io.github.nationalaudience.thetribunal.constant.SearchStaticValues.PARAMETER_SEARCH_TYPE;

@Controller
public class RankingController {

    private final GameRepository gameRepository;

    private record GameScore (String name, float score) {}

    public RankingController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping(END_POINT_RANKING)
    public String showRanking(Model model,
                              @RequestParam("size") int pageSize) {

        var averageScores = gameRepository.getAllAverageScores(PageRequest.of(0, pageSize));
        var gamesOrderByScore = gameRepository.getAllGamesByHighScore();

        var gameScores = new ArrayList<GameScore>();
        for (int i = 0; i < averageScores.size(); i++) {
            gameScores.add(new GameScore(gamesOrderByScore.get(i), averageScores.get(i)));
        }

        model.addAttribute("games", gameScores);


        return RANKING_TEMPLATE;
    }


}