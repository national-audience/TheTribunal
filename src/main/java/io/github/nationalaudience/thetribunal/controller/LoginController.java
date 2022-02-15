package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static io.github.nationalaudience.thetribunal.constant.LoginStaticValues.*;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping(END_POINT_LOGIN)
    public String login(Model model) {
        return TEMPLATE_LOGIN;
    }

    @PostMapping(END_POINT_POST_LOGIN)
    public String postLogin(
            Model model,
            @RequestParam(PARAMETER_USER) String postUser
    ) {
        var user = userRepository.findByUsername(postUser);
        model.addAttribute(ATTRIBUTE_USER_FOUND, user.isPresent());
        model.addAttribute(ATTRIBUTE_USER_NOT_FOUND, user.isEmpty());
        return TEMPLATE_LOGIN;
    }

}
