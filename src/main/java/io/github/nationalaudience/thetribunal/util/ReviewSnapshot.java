package io.github.nationalaudience.thetribunal.util;

import java.util.Date;

public record ReviewSnapshot(
        String user,
        String username,
        String game,
        String comment,
        int score,
        Date date,
        boolean canBeDeleted
) {
}
