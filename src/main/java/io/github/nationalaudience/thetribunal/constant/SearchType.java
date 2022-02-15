package io.github.nationalaudience.thetribunal.constant;


import java.util.Arrays;
import java.util.Optional;

public enum SearchType {

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
