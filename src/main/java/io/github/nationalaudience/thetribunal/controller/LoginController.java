package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

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

    @GetMapping(END_POINT_ERROR_LOGIN)
    public String postLogin(Model model) {
        model.addAttribute(ATTRIBUTE_INVALID_LOGIN, true);
        return TEMPLATE_LOGIN;
    }

    @GetMapping(END_POINT_SIGNUP)
    public String signup(Model model) {
        model.addAttribute(PARAMETER_USER, "");
        return TEMPLATE_SIGNUP;
    }

    @PostMapping(END_POINT_POST_SIGNUP)
    public String postSignup(
            Model model,
            @RequestParam(PARAMETER_USER) String postUser,
            @RequestParam(PARAMETER_NAME) String postName,
            @RequestParam(PARAMETER_PASSWORD) String postPassword,
            @RequestParam(PARAMETER_PASSWORD_CONFIRM) String postPasswordConfirm
    ) {

        model.addAttribute(PARAMETER_USER, postUser);

        postPassword = postPassword.trim();
        postPasswordConfirm = postPasswordConfirm.trim();

        if (postUser.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "User field cannot be empty!");
            return TEMPLATE_SIGNUP;
        }

        if (postName.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Name field cannot be empty!");
            return TEMPLATE_SIGNUP;
        }

        if (postPassword.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Password field cannot be empty!");
            return TEMPLATE_SIGNUP;
        }

        if (!postPassword.equals(postPasswordConfirm)) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Passwords don't match!");
            return TEMPLATE_SIGNUP;
        }

        var user = userRepository.findByUsername(postUser);
        if (user.isPresent()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "The user "
                    + user.get().getUsername()
                    + " is already registered!");
            return TEMPLATE_SIGNUP;
        }

        var newUser = new User(
                postUser,
                BCrypt.hashpw(postPassword, BCrypt.gensalt()),
                postName,
                "",
                false,
                "en-us",
                false,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        userRepository.save(newUser);

        model.addAttribute(ATTRIBUTE_USERNAME, postUser);
        return TEMPLATE_SUCCESSFUL_SIGNUP;
    }

}
