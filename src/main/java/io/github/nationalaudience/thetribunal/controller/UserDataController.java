package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static io.github.nationalaudience.thetribunal.constant.UserDataStaticValues.*;

@Controller
public class UserDataController {

    private final UserRepository userRepository;

    public UserDataController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(END_POINT_USER_DATA)
    public String userData(Model model, @PathVariable(PARAMETER_USER) String inUsername) {
        var optional = userRepository.findByUsername(inUsername);

        if (optional.isPresent()) {
            var user = optional.get();
            String count_followers = String.valueOf(user.getFollowedByUsers().size());
            String count_follows = String.valueOf(user.getUsersFollow().size());

            model.addAttribute(ATTRIBUTE_USER, user);
            model.addAttribute("count_followers", count_followers);
            model.addAttribute("count_follows", count_follows);
            return TEMPLATE_USER_DATA;
        } else {
            return TEMPLATE_USER_DATA_NOT_FOUND;
        }
    }

    @GetMapping(END_POINT_USER_DATA + "/follows")
    public String userDataFollowers(Model model, @PathVariable(PARAMETER_USER) String inUsername) {
        var optional = userRepository.findByUsername(inUsername);

        if (optional.isPresent()) {
            var user = optional.get();
            model.addAttribute(ATTRIBUTE_USER, user);

            return TEMPLATE_USER_DATA_FOLLOWS;
        } else {
            return TEMPLATE_USER_DATA_NOT_FOUND;
        }
    }
}
