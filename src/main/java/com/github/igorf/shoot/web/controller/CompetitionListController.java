package com.github.igorf.shoot.web.controller;

import com.github.igorf.shoot.logic.domain.Competition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
public class CompetitionListController {
    @RequestMapping("/")
    public String list(Model model) {
        Competition c = new Competition();
        c.setId(10000);
        c.setTitle("Test competition");

        model.addAttribute("competitions", Collections.singletonList(c));
        return "CompetitionList";
    }
}
