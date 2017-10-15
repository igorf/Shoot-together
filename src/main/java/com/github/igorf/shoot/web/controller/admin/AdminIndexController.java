package com.github.igorf.shoot.web.controller.admin;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_ADMIN")
public class AdminIndexController {
    @RequestMapping("/admin")
    public String index() {
        return "redirect:/admin/competition/list";
    }
}
