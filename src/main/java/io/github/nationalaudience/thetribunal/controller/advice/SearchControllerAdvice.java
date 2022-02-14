package io.github.nationalaudience.thetribunal.controller.advice;

import io.github.nationalaudience.thetribunal.constant.SearchType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

import static io.github.nationalaudience.thetribunal.constant.SearchStaticValues.*;

@ControllerAdvice
public class SearchControllerAdvice {

    @ModelAttribute(CACHE_LAST_SEARCH_QUERY)
    private String lastSearchQuery(HttpSession session) {
        var value = session.getAttribute(CACHE_LAST_SEARCH_QUERY);
        if (value == null) return "";
        return value.toString();
    }

    @ModelAttribute(CACHE_LAST_SEARCH_TYPE)
    private boolean isAllSelected(HttpSession session) {
        return SearchType.ALL.equals(session.getAttribute(CACHE_LAST_SEARCH_TYPE));
    }

    @ModelAttribute(CACHE_GAMES_SELECTED)
    private boolean isGamesSelected(HttpSession session) {
        return SearchType.GAMES.equals(session.getAttribute(CACHE_LAST_SEARCH_TYPE));
    }

    @ModelAttribute(CACHE_STUDIOS_SELECTED)
    private boolean isStudiosSelected(HttpSession session) {
        return SearchType.STUDIOS.equals(session.getAttribute(CACHE_LAST_SEARCH_TYPE));
    }

    @ModelAttribute(CACHE_USERS_SELECTED)
    private boolean isUsersSelected(HttpSession session) {
        return SearchType.USERS.equals(session.getAttribute(CACHE_LAST_SEARCH_TYPE));
    }

}
