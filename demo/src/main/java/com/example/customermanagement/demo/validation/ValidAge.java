package com.example.customermanagement.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidateAge.class)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int value();
    String message() default "Age must be at least {value} years old";

}
