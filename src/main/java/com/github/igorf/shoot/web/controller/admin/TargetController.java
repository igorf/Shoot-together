package com.github.igorf.shoot.web.controller.admin;

import com.github.igorf.shoot.logic.dao.TargetDao;
import com.github.igorf.shoot.logic.domain.Target;
import com.github.igorf.shoot.logic.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/target")
@Secured("ROLE_ADMIN")
public class TargetController {
    @Autowired private TargetDao targetDao;
    @Autowired private TargetService targetService;
    private Logger logger = Logger.getLogger(TargetController.class.getName());

    @RequestMapping("/list")
    public String list(Model model) {
        try {
            model.addAttribute("targets", targetDao.findAll());
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
        return "target/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        targetService.removeTarget(id);
        return "redirect:/admin/target/list";
    }

    @RequestMapping("/create")
    public String create() {
        return "/target/edit";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") long id) {
        model.addAttribute("target", targetDao.findById(id).orElse(null));
        return "/target/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String edit(Model model, @RequestParam(value = "id", required = false, defaultValue = "-1") long id, @RequestParam("title") String title) {
        Target target;
        if (id != -1) {
            target = targetService.updateTarget(id, title);
        } else {
            target = targetService.createTarget(title);
        }
        model.addAttribute("target", target);
        return "/target/edit";
    }
}
