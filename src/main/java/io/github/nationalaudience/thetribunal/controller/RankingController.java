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

    private record GameScore(int index, String name, float score) {
    }

    public RankingController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping(END_POINT_RANKING)
    public String showRanking(Model model,
                              @RequestParam(value = "pageSize", defaultValue = "3") int pageSize,
                              @RequestParam(value = "sortPageBy", defaultValue = "BEST") String directionS) {

        var gamesOrderByScore = gameRepository.getAllGamesByHighScore();
        var averageScores = gameRepository.getAllAverageScores(PageRequest.of(0, pageSize));

        var gameScores = new ArrayList<GameScore>();

        boolean orderByBest = false;

        if (directionS.equals("BEST")) {
            orderByBest = true;
            for (int i = 0; i < averageScores.size(); i++) {
                gameScores.add(new GameScore(i+1, gamesOrderByScore.get(i), averageScores.get(i)));
            }
        } else if (directionS.equals("WORST")) {
            for (int i = 0; i < averageScores.size(); i++) {
                gameScores.add(new GameScore(averageScores.size() - i,
                        gamesOrderByScore.get(averageScores.size() - 1 - i),
                        averageScores.get(averageScores.size() - 1 - i)));
            }
        }

        model.addAttribute("orderByBest", orderByBest);
        model.addAttribute("games", gameScores);


        return RANKING_TEMPLATE;
    }


}
