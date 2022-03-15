package io.github.nationalaudience.thetribunal.util;

import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ReviewUtils {

    public static Set<ReviewSnapshot> makeReviewSnapshot(Collection<Review> reviews, User loggedUser) {
        return reviews.stream()
                .map(it -> new ReviewSnapshot(
                        it.getUser().getName(),
                        it.getUser().getUsername(),
                        it.getGame().getName(),
                        it.getComment(),
                        it.getScore(),
                        it.getDate(),
                        loggedUser != null
                                && (loggedUser.isAdmin() ||
                                it.getUser().getUsername().equals(loggedUser.getUsername())))
                )
                .collect(Collectors.toSet());
    }

}
