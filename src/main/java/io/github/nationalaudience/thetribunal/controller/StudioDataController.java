package io.github.nationalaudience.thetribunal.controller;

import io.github.nationalaudience.thetribunal.repository.StudioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static io.github.nationalaudience.thetribunal.constant.StudioDataStaticValues.*;

@Controller
public class StudioDataController {

    private final StudioRepository studioRepository;

    public StudioDataController(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @GetMapping(END_POINT_STUDIO_DATA)
    public String userData(Model model, @PathVariable(PARAMETER_STUDIO) String inName) {
        var optional = studioRepository.findByName(inName);

        if (optional.isPresent()) {
            var studio = optional.get();
            model.addAttribute(ATTRIBUTE_STUDIO_NAME, studio);
            return TEMPLATE_STUDIO_DATA;
        } else {
            return TEMPLATE_STUDIO_DATA_NOT_FOUND;
        }
    }
}
