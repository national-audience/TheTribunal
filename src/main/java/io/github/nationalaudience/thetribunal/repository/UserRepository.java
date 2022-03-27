package io.github.nationalaudience.thetribunal.repository;

import io.github.nationalaudience.thetribunal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByNameContainingIgnoreCase(String name);

    Optional<User> findByUsername(String name);

    @Query(value = "select email from user",
            nativeQuery = true)
    List<String> findAllEmails();
}
