package io.github.nationalaudience.thetribunal.repository;

import io.github.nationalaudience.thetribunal.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByNameContainingIgnoreCase(String name);

}
