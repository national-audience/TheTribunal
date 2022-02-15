package io.github.nationalaudience.thetribunal.repository;

import io.github.nationalaudience.thetribunal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByNameContainingIgnoreCase(String name);

}
