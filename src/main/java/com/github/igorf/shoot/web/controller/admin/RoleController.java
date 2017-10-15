package com.github.igorf.shoot.web.controller.admin;

import com.github.igorf.shoot.logic.dao.RoleDao;
import com.github.igorf.shoot.logic.domain.Role;
import com.github.igorf.shoot.logic.service.RoleService;
import com.github.igorf.shoot.logic.validator.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/role")
@Secured("ROLE_ADMIN")
public class RoleController {
    @Autowired private RoleDao roleDao;
    @Autowired private RoleService roleService;
    @Autowired private RoleValidator roleValidator;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "admin-role/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        roleService.deleteRoleByID(id);
        return "redirect:/admin/role/list";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return "/admin-role/edit";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") long id) {
        model.addAttribute("role", roleDao.findOne(id));
        return "/admin-role/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String edit(@ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
        roleValidator.validate(role, bindingResult);
        if (!bindingResult.hasErrors()) {
            role = roleService.saveRole(role);
        }

        model.addAttribute("role", role);
        return "/admin-role/edit";
    }
}
