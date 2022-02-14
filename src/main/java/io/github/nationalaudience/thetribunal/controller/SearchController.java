package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.repository.GameRepository;
import io.github.nationalaudience.thetribunal.repository.StudioRepository;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

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

    @GetMapping("/search")
    public String searchGame(
            Model model,
            @RequestParam("searchType") String type,
            @RequestParam("query") String query
    ) {
        var searchType = SearchType.getByName(type).orElse(SearchType.ALL);

        var games = searchType.mustSearchGames()
                ? gameRepository.findByNameContainingIgnoreCase(query) : Collections.emptyList();
        var studios = searchType.mustSearchStudios()
                ? studioRepository.findByNameContainingIgnoreCase(query) : Collections.emptyList();
        var users = searchType.mustSearchUsers()
                ? userRepository.findByNameContainingIgnoreCase(query) : Collections.emptyList();

        model.addAttribute("games", games);
        model.addAttribute("hasGames", !games.isEmpty());
        model.addAttribute("studios", studios);
        model.addAttribute("hasStudios", !studios.isEmpty());
        model.addAttribute("users", users);
        model.addAttribute("hasUsers", !users.isEmpty());
        return "search/search_template";
    }


    private enum SearchType {

        ALL(true, true, true),
        GAMES(true, false, false),
        STUDIOS(false, true, false),
        USERS(false, false, true);

        private final boolean searchGames;
        private final boolean searchStudios;
        private final boolean searchUsers;

        SearchType(boolean searchGames, boolean searchStudios, boolean searchUsers) {
            this.searchGames = searchGames;
            this.searchStudios = searchStudios;
            this.searchUsers = searchUsers;
        }

        public boolean mustSearchGames() {
            return searchGames;
        }

        public boolean mustSearchStudios() {
            return searchStudios;
        }

        public boolean mustSearchUsers() {
            return searchUsers;
        }


        public static Optional<SearchType> getByName(String name) {
            return Arrays.stream(values()).filter(it -> it.name().equalsIgnoreCase(name)).findAny();
        }
    }

}
