package io.github.nationalaudience.thetribunal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static io.github.nationalaudience.thetribunal.constant.ErrorStaticValues.*;

@Controller
public class ErrorController {

    @GetMapping(END_POINT_ERROR)
    public String home(Model model) {
        return TEMPLATE_ERROR;
    }

}
