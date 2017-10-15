package com.github.igorf.shoot.web.controller;

import com.github.igorf.shoot.logic.auth.SecurityService;
import com.github.igorf.shoot.logic.domain.Competitor;
import com.github.igorf.shoot.logic.service.CompetitorService;
import com.github.igorf.shoot.logic.validator.CompetitorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompetitorProfileController {

    @Autowired private CompetitorService competitorService;
    @Autowired private CompetitorValidator competitorValidator;
    @Autowired private SecurityService securityService;

    @RequestMapping("/profile")
    public String index(Model model) {
        model.addAttribute("competitor", competitorService.getCurrent());
        return "profile/my";
    }

    @RequestMapping("/profile/save")
    public String save(@ModelAttribute("competitor")Competitor competitor, BindingResult bindingResult, Model model) {
        competitorValidator.validate(competitor, bindingResult);
        if (competitor.getProfile() == null) {
            competitor.setProfile(securityService.getLoggedProfile());
        }
        if (!bindingResult.hasErrors()) {
            competitorService.saveCompetitor(competitor);
            return "redirect:/profile";
        }
        model.addAttribute("competitor", competitor);
        return "profile/my";
    }
}
