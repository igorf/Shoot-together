package com.github.igorf.shoot.web.controller;

import com.github.igorf.shoot.logic.auth.SecurityService;
import com.github.igorf.shoot.logic.dto.ProfileDTO;
import com.github.igorf.shoot.logic.service.ProfileService;
import com.github.igorf.shoot.logic.validator.ProfileValidator;
import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import com.github.mkopylec.recaptcha.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    @Autowired private ProfileService profileService;
    @Autowired private SecurityService securityService;
    @Autowired private ProfileValidator profileValidator;
    @Autowired private RecaptchaValidator recaptchaValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new ProfileDTO());

        return "auth/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(HttpServletRequest request, @ModelAttribute("userForm") ProfileDTO userForm, BindingResult bindingResult) {
        profileValidator.validate(userForm, bindingResult);
        ValidationResult captchaValidationResult = recaptchaValidator.validate(request);
        if (captchaValidationResult.isFailure()) {
            bindingResult.rejectValue("captcha","Robot.error");
        }

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        profileService.saveProfile(userForm.createProfile());
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