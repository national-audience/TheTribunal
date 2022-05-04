package io.github.nationalaudience.thetribunal.repository;

import io.github.nationalaudience.thetribunal.entity.Game;
import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
        Optional<Review> findByUserAndGame(User user, Game game);

        @CacheEvict(value="feed", allEntries = true)
        @Override
        <S extends Review> List<S> saveAll(Iterable<S> entities);

        @CacheEvict(value="feed", allEntries = true)
        @Override
        <S extends Review> S saveAndFlush(S entity);

        @CacheEvict(value="feed", allEntries = true)
        @Override
        <S extends Review> List<S> saveAllAndFlush(Iterable<S> entities);

        @CacheEvict(value="feed", allEntries = true)
        @Override
        <S extends Review> S save(S entity);
}
