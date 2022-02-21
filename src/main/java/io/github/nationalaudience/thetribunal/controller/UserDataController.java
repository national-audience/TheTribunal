package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static io.github.nationalaudience.thetribunal.constant.GameDataStaticValues.ATTRIBUTE_ERROR_MESSAGE;
import static io.github.nationalaudience.thetribunal.constant.GenericDataStaticValues.*;
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
            String count_studio_follows = String.valueOf(user.getStudiosFollow().size());

            model.addAttribute(ATTRIBUTE_USER, user);
            model.addAttribute(ATTRIBUTE_USER_FOLLOWERS, count_followers);
            model.addAttribute(ATTRIBUTE_USER_FOLLOWS, count_follows);
            model.addAttribute(ATTRIBUTE_USER_STUDIO_FOLLOWS, count_studio_follows);
            model.addAttribute(ATTRIBUTE_DATA, inUsername);
            model.addAttribute(ATTRIBUTE_TYPE, "user");
            return TEMPLATE_USER_DATA;
        } else {
            return TEMPLATE_USER_DATA_NOT_FOUND;
        }
    }

    @PostMapping(END_POINT_USER_DATA)
    public String newFollowerUserData(Model model, @PathVariable(PARAMETER_USER) String inUsername,
                                      @RequestParam(PARAMETER_FOLLOWER) String follower) {

        var optional = userRepository.findByUsername(inUsername);

        if (optional.isPresent()) {
            var user = optional.get();

            if (inUsername.equals(follower)) {
                model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "User can not follow itself!");
            } else if (follower.isEmpty()) {
                model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Follower field cannot be empty!");
            } else {
                var userF = userRepository.findByUsername(follower);
                if (userF.isEmpty()) {
                    model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "The user "
                            + follower
                            + " is not registered!");
                } else {
                    var userFollows = userF.get().getUsersFollow();

                    if (!userFollows.contains(user)) {
                        userFollows.add(user);
                        userF.get().setUsersFollow(userFollows);

                        userRepository.save(userF.get());
                    } else {
                        model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "The user "
                                + userF.get().getName()
                                + " is following this user already!");
                    }
                }
            }

            String count_followers = String.valueOf(user.getFollowedByUsers().size());
            String count_follows = String.valueOf(user.getUsersFollow().size());
            String count_studio_follows = String.valueOf(user.getStudiosFollow().size());

            model.addAttribute(ATTRIBUTE_USER, user);
            model.addAttribute(ATTRIBUTE_USER_FOLLOWERS, count_followers);
            model.addAttribute(ATTRIBUTE_USER_FOLLOWS, count_follows);
            model.addAttribute(ATTRIBUTE_USER_STUDIO_FOLLOWS, count_studio_follows);
            model.addAttribute(ATTRIBUTE_DATA, inUsername);
            model.addAttribute(ATTRIBUTE_TYPE, "user");
            return TEMPLATE_USER_DATA;
        } else {
            return TEMPLATE_USER_DATA_NOT_FOUND;
        }
    }

    @GetMapping(END_POINT_USER_FOLLOWS_DATA)
    public String userDataFollows(Model model, @PathVariable(PARAMETER_USER) String inUsername) {
        var optional = userRepository.findByUsername(inUsername);

        if (optional.isPresent()) {
            var user = optional.get();
            model.addAttribute(ATTRIBUTE_USER, user);

            return TEMPLATE_USER_DATA_FOLLOWS;
        } else {
            return TEMPLATE_USER_DATA_NOT_FOUND;
        }
    }

    @GetMapping(END_POINT_USER_FOLLOWERS_DATA)
    public String userDataFollowers(Model model, @PathVariable(PARAMETER_USER) String inUsername) {
        var optional = userRepository.findByUsername(inUsername);

        if (optional.isPresent()) {
            var user = optional.get();
            model.addAttribute(ATTRIBUTE_USER, user);

            return TEMPLATE_USER_DATA_FOLLOWERS;
        } else {
            return TEMPLATE_USER_DATA_NOT_FOUND;
        }
    }

    @GetMapping(END_POINT_USER_STUDIOS_FOLLOWS_DATA)
    public String userDataStudioFollows(Model model, @PathVariable(PARAMETER_USER) String inUsername) {
        var optional = userRepository.findByUsername(inUsername);

        if (optional.isPresent()) {
            var user = optional.get();
            model.addAttribute(ATTRIBUTE_USER, user);

            return TEMPLATE_USER_DATA_STUDIOS_FOLLOWS;
        } else {
            return TEMPLATE_USER_DATA_NOT_FOUND;
        }
    }

    @PostMapping(END_POINT_DELETE_USER_DATA)
    public String deleteUserData(Model model, @PathVariable(PARAMETER_USER) String inUsername) {

        var user = userRepository.findByUsername(inUsername);

        if (user.isPresent()){
            var followers = user.get().getFollowedByUsers();
            for (var follower : followers) {
                var follows = follower.getUsersFollow();
                follows.remove(user.get());
                follower.setUsersFollow(follows);
            }
        }

        user.ifPresent(userRepository::delete);

        model.addAttribute(ATTRIBUTE_TYPE, "user");
        model.addAttribute(ATTRIBUTE_DATA, inUsername);

        return TEMPLATE_DATA_DELETED;
    }
}