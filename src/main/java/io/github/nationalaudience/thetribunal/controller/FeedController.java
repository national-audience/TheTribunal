package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.constant.LoginStaticValues;
import io.github.nationalaudience.thetribunal.entity.Game;
import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.services.FeedResponse;
import io.github.nationalaudience.thetribunal.services.FeedService;
import io.github.nationalaudience.thetribunal.util.ReviewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.github.nationalaudience.thetribunal.constant.UserDataStaticValues.ATTRIBUTE_USER_REVIEWS;

@Controller
public class FeedController {

    @Autowired
    FeedService feedService;

    @GetMapping(value = "/feed")
    public String generateFeed(Model model) throws URISyntaxException {
        //var user = (User) model.getAttribute(LoginStaticValues.CACHE_LOGGED_USER);

        List<FeedResponse> feedResponses = feedService.getFeedFromInternalService();

        model.addAttribute("user_reviews", feedResponses);

        for (FeedResponse fr: feedResponses) {
            model.addAttribute("username", fr.getUsername());
            model.addAttribute("game", fr.getGame());
            model.addAttribute("comment", fr.getComment());
            model.addAttribute("score", fr.getScore());
            model.addAttribute("date", fr.getDate());
        }


        return "feed/feed_template";
    }
}
