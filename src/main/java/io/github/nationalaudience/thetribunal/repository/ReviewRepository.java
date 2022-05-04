package io.github.nationalaudience.thetribunal.repository;

import io.github.nationalaudience.thetribunal.entity.Game;
import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
        Optional<Review> findByUserAndGame(User user, Game game);

}
