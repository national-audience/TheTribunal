package io.github.nationalaudience.thetribunal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static io.github.nationalaudience.thetribunal.constant.HomeStaticValues.END_POINT_HOME;
import static io.github.nationalaudience.thetribunal.constant.HomeStaticValues.TEMPLATE_HOME;

@Controller
public class HomeController {

    @GetMapping(END_POINT_HOME)
    public String home() {
        return TEMPLATE_HOME;
    }

}
