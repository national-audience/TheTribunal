package io.github.nationalaudience.thetribunal;

import io.github.nationalaudience.thetribunal.entity.Game;
import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.Studio;
import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.repository.GameRepository;
import io.github.nationalaudience.thetribunal.repository.ReviewRepository;
import io.github.nationalaudience.thetribunal.repository.StudioRepository;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DataBaseUsage implements CommandLineRunner {

    private final UserRepository userRepository;
    private final StudioRepository studioRepository;
    private final GameRepository gameRepository;
    private final ReviewRepository reviewRepository;

    public DataBaseUsage(UserRepository userRepository, StudioRepository studioRepository, GameRepository gameRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.studioRepository = studioRepository;
        this.gameRepository = gameRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) {

        //var rafa = userRepository.findByUsername("rafa2").orElseThrow();
        //var pepe = userRepository.findByUsername("pepe1").orElseThrow();
        //Hibernate.initialize(rafa);
        //rafa.getUsersFollow().add(pepe);
        //userRepository.save(rafa);

        //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
//
        //Studio s1 = new Studio("Nintendo", "Japoneses", "Japon", 123, new ArrayList<>(), new ArrayList<>());
        //Studio s2 = new Studio("Sony", "Japoneses", "Japon", 213, new ArrayList<>(), new ArrayList<>());
//
        //Game g1 = new Game("Mario", "Salto", new ArrayList<>(), new Date(), new ArrayList<>());
        //Game g2 = new Game("Zelda", "No Salto", new ArrayList<>(), new Date(), new ArrayList<>());
        //Game g3 = new Game("Kratos", "No Salto", new ArrayList<>(), new Date(), new ArrayList<>());
        //Game g4 = new Game("Uncharted", "Salto", new ArrayList<>(), new Date(), new ArrayList<>());
        //Game g5 = new Game("Comun", "Salto", new ArrayList<>(), new Date(), new ArrayList<>());
//
        //g1.setStudios(List.of(s1));
        //g2.setStudios(List.of(s1));
        //g3.setStudios(List.of(s2));
        //g4.setStudios(List.of(s2));
        //g5.setStudios(List.of(s1, s2));
//
        //User u1 = new User("pepe" + 1,
        //        "iugshjkgdhu",
        //        "Pepe Juan",
        //        "Soy Pepe",
        //        true,
        //        "en-us",
        //        false,
        //        List.of(s1, s2),
        //        new ArrayList<>(),
        //        new ArrayList<>(),
        //        new ArrayList<>());
//
        //User u2 = new User("Rafa" + 2,
        //        "iugshjkgdhu",
        //        "Rafa Exposito",
        //        "Soy Rafa",
        //        false,
        //        "es-es",
        //        true,
        //        List.of(s1),
        //        new ArrayList<>(),
        //        new ArrayList<>(),
        //        new ArrayList<>());
//
        //u1.setUsersFollow(List.of(u2));
//
        //Review r1 = new Review(10, "adkfñljas", new Date(), u1, g1);
        //Review r2 = new Review(5, "adkfñljas", new Date(), u1, g2);
        //Review r3 = new Review(3, "adkfñljas", new Date(), u1, g3);
        //Review r4 = new Review(1, "adkfñljas", new Date(), u2, g1);
        //Review r5 = new Review(2, "adkfñljas", new Date(), u2, g2);
//
        //studioRepository.save(s1);
        //studioRepository.save(s2);
//
        //userRepository.save(u2);
        //userRepository.save(u1);
//
        //gameRepository.save(g1);
        //gameRepository.save(g2);
        //gameRepository.save(g3);
        //gameRepository.save(g4);
        //gameRepository.save(g5);
//
        //reviewRepository.save(r1);
        //reviewRepository.save(r2);
        //reviewRepository.save(r3);
        //reviewRepository.save(r4);
        //reviewRepository.save(r5);

    }
}
