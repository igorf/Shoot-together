package com.github.igorf.shoot.web.controller.admin;

import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.Exercise;
import com.github.igorf.shoot.logic.service.AdminCompetitionService;
import com.github.igorf.shoot.logic.service.ExerciseService;
import com.github.igorf.shoot.logic.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/competition")
public class AdminCompetitionController {
    @Autowired private ExerciseService exerciseService;
    @Autowired private AdminCompetitionService competitionService;
    private Logger logger = Logger.getLogger(AdminCompetitionController.class.getName());

    @RequestMapping("/list")
    public String list(Model model, @PageableDefault(value = 30) Pageable pageable) {
        model.addAttribute("competitions", competitionService.listPage(pageable));
        return "/admin-competition/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        competitionService.removeCompetition(id);
        return "redirect:/admin/competition/list";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("exercises", exerciseService.listToChoice());
        model.addAttribute("isMutable", true);
        return "/admin-competition/edit";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") long id) {
        Competition competition = competitionService.findById(id);
        if (competition == null) {
            return "redirect:/admin/competition/list";
        }
        model.addAttribute("competition", competition);
        model.addAttribute("exercises", exerciseService.listToChoice());
        model.addAttribute("isMutable", competition.isMutable());
        return "/admin-competition/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            Model model,
            @RequestParam(value = "id", required = false, defaultValue = "-1") long id,
            @RequestParam("title") String title,
            @RequestParam("exerciseId") long exerciseId,
            @RequestParam("start") @DateTimeFormat(pattern="dd/MM/yyyy")Date start,
            @RequestParam("end") @DateTimeFormat(pattern="dd/MM/yyyy")Date end
    ) {
        Competition competition;
        if (id == -1) {
            competition = competitionService.createCompetition(title, exerciseId, start, end);
        } else {
            competition = competitionService.updateCompetition(id, title, exerciseId, start, end);
        }

        if (competition != null) {
            model.addAttribute("competition", competition);
            model.addAttribute("exercises", exerciseService.listToChoice());
            return "/admin-competition/edit";
        }
        return "redirect:/admin/competition/list";
    }
}