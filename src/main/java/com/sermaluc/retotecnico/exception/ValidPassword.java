package com.sermaluc.retotecnico.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME
)
public @interface ValidPassword {

    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}