package com.github.igorf.shoot.web.controller;

import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.Exercise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    @RequestMapping("/list")
    public String list(Model model) {

        Exercise e = new Exercise();
        e.setId(123);
        e.setTitle("AP-40");

        Competition c = new Competition();
        c.setId(11000);
        c.setTitle("Test competition");
        c.setExercise(e);

        model.addAttribute("competitions", Collections.singletonList(c));
        return "competition/list";
    }
}
