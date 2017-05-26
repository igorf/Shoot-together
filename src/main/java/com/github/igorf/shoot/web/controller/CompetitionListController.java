package com.github.igorf.shoot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompetitionListController {
    @RequestMapping("/")
    public String list() {
        return "CompetitionList";
    }
}
