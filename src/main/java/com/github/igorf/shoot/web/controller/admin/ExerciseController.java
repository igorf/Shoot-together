package com.github.igorf.shoot.web.controller.admin;

import com.github.igorf.shoot.logic.domain.Exercise;
import com.github.igorf.shoot.logic.service.ExerciseService;
import com.github.igorf.shoot.logic.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/exercise")
@Secured("ROLE_ADMIN")
public class ExerciseController {
    @Autowired private ExerciseService exerciseService;
    @Autowired private TargetService targetService;
    private Logger logger = Logger.getLogger(ExerciseController.class.getName());

    @RequestMapping("/list")
    public String list(Model model, @PageableDefault(value = 30) Pageable pageable) {
        model.addAttribute("exercises", exerciseService.listPage(pageable));
        return "/exercise/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        exerciseService.removeExercise(id);
        return "redirect:/admin/exercise/list";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("targets", targetService.listToChoice());
        return "/exercise/edit";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") long id) {
        model.addAttribute("exercise", exerciseService.findById(id));
        model.addAttribute("targets", targetService.listToChoice());
        return "/exercise/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            Model model,
            @RequestParam(value = "id", required = false, defaultValue = "-1") long id,
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            @RequestParam("shots") int shots,
            @RequestParam("timeLimit") int timeLimit,
            @RequestParam("caliber") float caliber,
            @RequestParam("distance") float distance,
            @RequestParam("shotsPerTarget") int shotsPerTarget,
            @RequestParam("shotsPerSeries") int shotsPerSeries,
            @RequestParam("targetId") long targetId
    ) {
        Exercise exercise;
        if (id == -1) {
            exercise = exerciseService.createExercise(title, description, shots, timeLimit, caliber, distance, shotsPerTarget, shotsPerSeries, targetId);
        } else {
            exercise = exerciseService.updateExercise(id, title, description, shots, timeLimit, caliber, distance, shotsPerTarget, shotsPerSeries, targetId);
        }

        if (exercise != null) {
            model.addAttribute("exercise", exercise);
            model.addAttribute("targets", targetService.listToChoice());
            return "/exercise/edit";
        }
        return "redirect:/admin/exercise/list";
    }
}
