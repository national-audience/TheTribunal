package io.github.nationalaudience.thetribunal.repository;

import io.github.nationalaudience.thetribunal.entity.Game;
import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
        @Cacheable("reviews")
        Optional<Review> findByUserAndGame(User user, Game game);

        @CacheEvict(value = "reviews", allEntries = true)
        @Override
        <S extends Review> S save(S entity);
}
