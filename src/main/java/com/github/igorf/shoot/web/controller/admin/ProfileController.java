package com.github.igorf.shoot.web.controller.admin;

import com.github.igorf.shoot.logic.domain.Profile;
import com.github.igorf.shoot.logic.service.ProfileService;
import com.github.igorf.shoot.logic.service.RoleService;
import com.github.igorf.shoot.logic.validator.ProfilePasswordValidator;
import com.github.igorf.shoot.logic.validator.ProfileUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/profile")
public class ProfileController {
    @Autowired private ProfileService profileService;
    @Autowired private RoleService roleService;
    @Autowired private ProfileUpdateValidator profileValidator;
    @Autowired private ProfilePasswordValidator profilePasswordValidator;

    @RequestMapping("/list")
    public String list(Model model, @PageableDefault(value = 50, sort = {"login"}, direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("profiles", profileService.getPage(pageable));
        return "admin-profile/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        profileService.deleteProfileByID(id);
        return "redirect:/admin/profile/list";
    }

    @RequestMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        model.addAttribute("profile", profileService.findByID(id));
        model.addAttribute("roles", roleService.findAll());

        return "/admin-profile/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String edit(@ModelAttribute("profile") Profile profile, BindingResult bindingResult, Model model) {
        profileValidator.validate(profile, bindingResult);
        if (!bindingResult.hasErrors()) {
            profile = profileService.saveProfile(profile);
            return "redirect:/admin/profile/view/" + profile.getId();
        }
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("profile", profile);
        return "/admin-profile/edit";
    }

    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public String updatePassword(@ModelAttribute("profile") Profile profile, BindingResult bindingResult, Model model) {
        profilePasswordValidator.validate(profile, bindingResult);
        if (!bindingResult.hasErrors()) {
            profile = profileService.updatePassword(profile);
            return "redirect:/admin/profile/view/" + profile.getId();
        }
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("profile", profile);
        return "/admin-profile/edit";
    }
}
