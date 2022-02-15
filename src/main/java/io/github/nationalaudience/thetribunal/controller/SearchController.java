package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.constant.SearchType;
import io.github.nationalaudience.thetribunal.repository.GameRepository;
import io.github.nationalaudience.thetribunal.repository.StudioRepository;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collections;

import static io.github.nationalaudience.thetribunal.constant.SearchStaticValues.*;

@Controller
public class SearchController {

    private final GameRepository gameRepository;
    private final StudioRepository studioRepository;
    private final UserRepository userRepository;

    public SearchController(GameRepository gameRepository, StudioRepository studioRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.studioRepository = studioRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(END_POINT_SEARCH)
    public String searchGame(
            Model model,
            HttpSession session,
            @RequestParam(PARAMETER_SEARCH_TYPE) String type,
            @RequestParam(PARAMETER_SEARCH_QUERY) String query
    ) {
        var searchType = SearchType.getByName(type).orElse(SearchType.ALL);

        // Override advice attributes.
        model.addAttribute(CACHE_LAST_SEARCH_TYPE, searchType);
        model.addAttribute(CACHE_LAST_SEARCH_QUERY, query);
        model.addAttribute(CACHE_ALL_SELECTED, searchType == SearchType.ALL);
        model.addAttribute(CACHE_GAMES_SELECTED, searchType == SearchType.GAMES);
        model.addAttribute(CACHE_STUDIOS_SELECTED, searchType == SearchType.STUDIOS);
        model.addAttribute(CACHE_USERS_SELECTED, searchType == SearchType.USERS);


        session.setAttribute(CACHE_LAST_SEARCH_TYPE, searchType);
        session.setAttribute(CACHE_LAST_SEARCH_QUERY, query);

        var games = searchType.mustSearchGames()
                ? gameRepository.findByNameContainingIgnoreCase(query) : Collections.emptyList();
        var studios = searchType.mustSearchStudios()
                ? studioRepository.findByNameContainingIgnoreCase(query) : Collections.emptyList();
        var users = searchType.mustSearchUsers()
                ? userRepository.findByNameContainingIgnoreCase(query) : Collections.emptyList();

        model.addAttribute(ATTRIBUTE_GAMES, games);
        model.addAttribute(ATTRIBUTE_HAS_GAMES, !games.isEmpty());
        model.addAttribute(ATTRIBUTE_STUDIOS, studios);
        model.addAttribute(ATTRIBUTE_HAS_STUDIOS, !studios.isEmpty());
        model.addAttribute(ATTRIBUTE_USERS, users);
        model.addAttribute(ATTRIBUTE_HAS_USERS, !users.isEmpty());
        return TEMPLATE_SEARCH;
    }

}
