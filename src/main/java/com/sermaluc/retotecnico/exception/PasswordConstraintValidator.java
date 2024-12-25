package com.sermaluc.retotecnico.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Value("${valid.password.min-size}")
    private int passwordMinSize;

    @Value("${valid.password.max-size}")
    private int passwordMaxSize;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator passwordValidator = new PasswordValidator(
                Arrays.asList(new LengthRule(passwordMinSize, passwordMaxSize),
                        new CharacterRule(EnglishCharacterData.UpperCase, 1),
                        new CharacterRule(EnglishCharacterData.LowerCase, 1),
                        new CharacterRule(EnglishCharacterData.Digit, 1),
                        new CharacterRule(EnglishCharacterData.Special, 1)
                )
        );
        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        //Sending one message each time failed validation.
        constraintValidatorContext.buildConstraintViolationWithTemplate(passwordValidator.getMessages(result).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
