package io.github.nationalaudience.thetribunal;

import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseUsage implements CommandLineRunner {

    private final UserRepository repository;

    public DataBaseUsage(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");

        for (int i = 0; i < 1; i++)
            repository.save(new User(
                    "pepe" + i,
                    "iugshjkgdhu",
                    "Pepe" + i,
                    "Soy Pepe",
                    true,
                    "en-us",
                    false
            ));


        repository.delete(repository.findUserByUsername("pepe0").get());
        var pepe = repository.findUserByUsername("pepe0");
        System.out.println(pepe);
    }
}
