package com.github.igorf.shoot.web.controller;

import com.github.igorf.shoot.logic.auth.SecurityService;
import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.CompetitionResult;
import com.github.igorf.shoot.logic.domain.Profile;
import com.github.igorf.shoot.logic.service.CompetitionResultService;
import com.github.igorf.shoot.logic.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/competition/result")
public class CompetitorTargetController {

    @Autowired private SecurityService securityService;
    @Autowired private CompetitionResultService competitionResultService;
    @Autowired private CompetitionService competitionService;

    @Secured("ROLE_COMPETITOR")
    @RequestMapping("/my/target/add/{competitionId}")
    public String add(Model model, @PathVariable("competitionId") Long competitionId) {
        Competition competition = competitionService.findById(competitionId);
        model.addAttribute("competition", competition);

        return "result/add_target";
    }

    @Secured("ROLE_COMPETITOR")
    @RequestMapping("/my/{competitionId}")
    public String my(Model model, @PathVariable("competitionId") Long competitionId) {
        Competition competition = competitionService.findById(competitionId);
        Profile profile = securityService.getLoggedProfile();
        CompetitionResult competitionResult
                = competitionResultService.findOrCreateCompetitionResult(competition, profile);

        model.addAttribute("competitionResult", competitionResult);
        return "result/my";
    }

    @Secured("ROLE_COMPETITOR")
    @RequestMapping("/my/{competitionId}/publish")
    public String publish(Model model, @PathVariable("competitionId") Long competitionId) {
        Competition competition = competitionService.findById(competitionId);
        Profile profile = securityService.getLoggedProfile();
        CompetitionResult competitionResult = competitionResultService.findOrCreateCompetitionResult(competition, profile);

        competitionResult = competitionResultService.publish(competitionResult);
        model.addAttribute("competitionResult", competitionResult);
        return "result/my";
    }

    @RequestMapping("/{resultId}")
    public String competitionResultView(Model model, @PathVariable("resultId") Long resultId) {
        CompetitionResult competitionResult = competitionResultService.getByID(resultId);
        model.addAttribute("competitionResult", competitionResult);
        return "result/view";
    }
}
