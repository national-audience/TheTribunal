package io.github.nationalaudience.thetribunal.repository;

import io.github.nationalaudience.thetribunal.entity.Game;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByNameContainingIgnoreCase(String name);

    @Query(value = "select avg(score) as scores from review inner join game g on review.game_id = g.id group by name order by avg(score) desc",
            nativeQuery = true)
    List<Float> getAllAverageScores(Pageable pageable);

    @Query(value = "select name from review inner join game g on review.game_id = g.id group by name order by avg(score) desc",
            nativeQuery = true)
    List<String> getAllGamesByHighScore();
}
