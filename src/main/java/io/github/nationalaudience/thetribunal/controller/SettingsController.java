package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.constant.LoginStaticValues;
import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.repository.GameRepository;
import io.github.nationalaudience.thetribunal.repository.ReviewRepository;
import io.github.nationalaudience.thetribunal.repository.StudioRepository;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.SortType;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import io.github.nationalaudience.thetribunal.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import io.github.nationalaudience.thetribunal.repository.UserRepository;

import static io.github.nationalaudience.thetribunal.constant.LoginStaticValues.ATTRIBUTE_ERROR_MESSAGE;
import static io.github.nationalaudience.thetribunal.constant.LoginStaticValues.TEMPLATE_SIGNUP;
import static io.github.nationalaudience.thetribunal.constant.SettingsStaticsValues.*;

@Controller
public class SettingsController {

    private final UserRepository userRepository;

    public SettingsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(END_POINT_SETTINGS)
    public String settingsMenu(Model model) {

        var user = (User) model.getAttribute(LoginStaticValues.CACHE_LOGGED_USER);

        if (user == null) {
            return SETTINGS_TEMPLATE;
        } else {
            model.addAttribute(ATTRIBUTE_USER_NAME, user);
        }
        return SETTINGS_TEMPLATE;
    }

    @PostMapping(END_POINT_SETTINGS_CONFIRM)
    public String settingsConfirm(Model model, @RequestParam("newName") String newName,
                                  @RequestParam("newDescription") String newDescription,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("newMail") String newMail) {
        var user = (User) model.getAttribute(LoginStaticValues.CACHE_LOGGED_USER);

        if (user == null) {
            return SETTINGS_TEMPLATE;
        } else {
            model.addAttribute(ATTRIBUTE_USER_NAME, user);
        }

        if(!newName.equals(""))
            user.setName(newName);
        if(!newDescription.equals(""))
        {
            user.setDescription(newDescription);
        }

        if(!newPassword.equals(""))
        {
            newPassword = newPassword.trim();
            user.setPasswordHash(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        }
        if(!newMail.equals(""))
        {
            var checkEmail = userRepository.findAllEmails();
            if (checkEmail.contains(checkEmail)) {
                model.addAttribute(ATTRIBUTE_ERROR, "The email "
                        + newMail
                        + " is already registered!");
                return SETTINGS_TEMPLATE;
            }
            user.setEmail(newMail);
        }


        userRepository.save(user);

        return SETTINGS_TEMPLATE;
    }
}
