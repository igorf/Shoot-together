package com.github.igorf.shoot.logic.validator;

import com.github.igorf.shoot.logic.domain.Competitor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CompetitorValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Competitor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty");
    }
}
