package com.github.igorf.shoot.web.controller;

import com.github.igorf.shoot.logic.auth.SecurityService;
import com.github.igorf.shoot.logic.domain.Profile;
import com.github.igorf.shoot.logic.service.ProfileService;
import com.github.igorf.shoot.logic.validator.ProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
    @Autowired private ProfileService profileService;
    @Autowired private SecurityService securityService;
    @Autowired private ProfileValidator profileValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Profile());

        return "auth/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Profile userForm, BindingResult bindingResult, Model model) {
        profileValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        profileService.createProfile(userForm);
        securityService.autologin(userForm.getLogin(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "auth/login";
    }
}