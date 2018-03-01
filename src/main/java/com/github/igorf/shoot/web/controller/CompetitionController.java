package com.github.igorf.shoot.web.controller;

import com.github.igorf.shoot.logic.auth.SecurityService;
import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.CompetitionResult;
import com.github.igorf.shoot.logic.service.CompetitionResultService;
import com.github.igorf.shoot.logic.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired CompetitionService competitionService;
    @Autowired CompetitionResultService resultService;
    @Autowired SecurityService securityService;

    @RequestMapping("/list")
    public String list(Model model, @PageableDefault(value = 30, sort = {"end"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("competitions", competitionService.listPage(pageable));
        return "competition/list";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        Competition competition = competitionService.findById(id);
        model.addAttribute("competition", competition);
        if (securityService.getLoggedProfile() != null) {
            model.addAttribute("myResult", resultService.findOrCreateCompetitionResult(competition, securityService.getLoggedProfile()));
        } else {
            model.addAttribute("myResult", null);
        }
        return "competition/view";
    }
}
