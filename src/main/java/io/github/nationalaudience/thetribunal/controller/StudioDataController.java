package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.constant.LoginStaticValues;
import io.github.nationalaudience.thetribunal.entity.Studio;
import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.repository.StudioRepository;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static io.github.nationalaudience.thetribunal.constant.GameDataStaticValues.ATTRIBUTE_ERROR_MESSAGE;
import static io.github.nationalaudience.thetribunal.constant.GenericDataStaticValues.*;
import static io.github.nationalaudience.thetribunal.constant.StudioDataStaticValues.*;

@Controller
public class StudioDataController {

    private final StudioRepository studioRepository;
    private final UserRepository userRepository;

    public StudioDataController(StudioRepository studioRepository, UserRepository userRepository) {
        this.studioRepository = studioRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(END_POINT_STUDIO_DATA)
    public String studioData(Model model, @PathVariable(PARAMETER_STUDIO) String inName) {
        var optional = studioRepository.findByName(inName);

        if (optional.isPresent()) {
            var studio = optional.get();
            String count_followers = String.valueOf(studio.getStudioFollowedByUsers().size());
            model.addAttribute(ATTRIBUTE_STUDIO_FOLLOWERS, count_followers);
            model.addAttribute(ATTRIBUTE_STUDIO_NAME, studio);
            model.addAttribute(ATTRIBUTE_TYPE, "studio");
            model.addAttribute(ATTRIBUTE_DATA, inName);
            return TEMPLATE_STUDIO_DATA;
        } else {
            return TEMPLATE_STUDIO_DATA_NOT_FOUND;
        }
    }

    @PostMapping(END_POINT_FOLLOW_STUDIO_DATA + "/{inName}")
    public String newFollowerStudioData(Model model, @PathVariable(PARAMETER_STUDIO) String inName) {

        var optional = studioRepository.findByName(inName);
        if (optional.isPresent()) {
            var studio = optional.get();
            var follower = (User) model.getAttribute(LoginStaticValues.CACHE_LOGGED_USER);

            if (follower == null) {
                model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Follower field cannot be empty!");
            } else {
                var studioFollow = follower.getStudiosFollow();
                if (!studioFollow.contains(studio)) {
                    studioFollow.add(studio);
                    userRepository.save(follower);
                } else {
                    model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "The user "
                            + follower.getName()
                            + " is following this studio already!");
                }
            }

            String count_followers = String.valueOf(studio.getStudioFollowedByUsers().size());
            model.addAttribute(ATTRIBUTE_STUDIO_FOLLOWERS, count_followers);
            model.addAttribute(ATTRIBUTE_STUDIO_NAME, studio);
            model.addAttribute(ATTRIBUTE_TYPE, "studio");
            model.addAttribute(ATTRIBUTE_DATA, inName);
            return TEMPLATE_STUDIO_DATA;
        } else {
            return TEMPLATE_STUDIO_DATA_NOT_FOUND;
        }
    }

    @GetMapping(END_POINT_STUDIO_FOLLOWERS_DATA)
    public String studioFollowersData(Model model, @PathVariable(PARAMETER_STUDIO) String inName) {
        var optional = studioRepository.findByName(inName);

        if (optional.isPresent()) {
            var studio = optional.get();
            model.addAttribute(ATTRIBUTE_STUDIO_NAME, studio);
            return TEMPLATE_STUDIO_FOLLOWERS_DATA;
        } else {
            return TEMPLATE_STUDIO_DATA_NOT_FOUND;
        }
    }

    @GetMapping(END_POINT_NEW_STUDIO_TO_DB)
    public String newStudioToDb(Model model) {
        return TEMPLATE_NEW_STUDIO;
    }

    @PostMapping(END_POINT_POST_NEW_STUDIO_TO_DB)
    public String postNewStudioToDb(
            Model model,
            @RequestParam(PARAMERTER_STUDIO_NAME) String studioName,
            @RequestParam(PARAMERTER_STUDIO_DESCRIPTION) String description,
            @RequestParam(PARAMERTER_STUDIO_EMPLOYEES) String employees,
            @RequestParam(PARAMERTER_STUDIO_LOCATION) String location) {

        if (studioName.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Studio name field cannot be empty!");
            return TEMPLATE_NEW_STUDIO;
        }

        if (description.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Description field cannot be empty!");
            return TEMPLATE_NEW_STUDIO;
        }

        if (employees.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Employees field cannot be empty!");
            return TEMPLATE_NEW_STUDIO;
        }

        int employeesInt = Integer.parseInt(employees);

        if (employeesInt < 1) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Employees number invalid!");
            return TEMPLATE_NEW_STUDIO;
        }

        if (location.isEmpty()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "Location field cannot be empty!");
            return TEMPLATE_NEW_STUDIO;
        }

        var studio = studioRepository.findByName(studioName);
        if (studio.isPresent()) {
            model.addAttribute(ATTRIBUTE_ERROR_MESSAGE, "The studio "
                    + studio.get().getName()
                    + " is already registered!");
            return TEMPLATE_NEW_STUDIO;
        }

        var newStudio = new Studio(
                studioName,
                description,
                location,
                employeesInt,
                new ArrayList<>(),
                new ArrayList<>()
        );

        studioRepository.save(newStudio);

        System.out.println("STUDIO ADDED");

        model.addAttribute(ATTRIBUTE_STUDIO_NAME, studioName);
        return TEMPLATE_POST_NEW_STUDIO;
    }

    @PostMapping(END_POINT_DELETE_STUDIO_DATA + "/{inName}")
    public String deleteStudioData(Model model, @PathVariable(PARAMETER_STUDIO) String inName) {

        var user = studioRepository.findByName(inName);

        user.ifPresent(studioRepository::delete);

        model.addAttribute(ATTRIBUTE_TYPE, "studio");
        model.addAttribute(ATTRIBUTE_DATA, inName);

        return TEMPLATE_DATA_DELETED;
    }
}
