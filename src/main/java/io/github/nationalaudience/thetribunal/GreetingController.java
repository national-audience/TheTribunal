package io.github.nationalaudience.thetribunal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(Model model){
        model.addAttribute("name","World");

        return "greeting_template";
    }

    @GetMapping("/conditional_greeting")
    public String conditionalGreeting(Model model){
        model.addAttribute("name","World");
        model.addAttribute("hello", true);

        return "conditional_greeting_template";
    }

    @GetMapping("/colors")
    public String iterateColors(Model model){
        var colors = Arrays.asList("Red", "Green", "Blue");
        model.addAttribute("colors",colors);

        return "colors_template";
    }

    @GetMapping("/objects")
    public String iterateObjects(Model model){
        var objects = new ArrayList<>();
        objects.add(new Person("Pepe", "Jimenez"));
        objects.add(new Person("Javier", "Mundial"));
        objects.add(new Person("Rafa", "Local"));
        model.addAttribute("people", objects);
        return "objects_template";
    }
}
