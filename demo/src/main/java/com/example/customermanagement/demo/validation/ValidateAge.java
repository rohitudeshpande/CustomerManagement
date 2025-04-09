package com.example.customermanagement.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidateAge implements ConstraintValidator<ValidAge, String> {
    private int minimumAge;
    private static final String pattern = "dd-MM-yyyy";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    @Override
    public void initialize(ValidAge constraint){
        this.minimumAge = constraint.value();
    }
    @Override
    public boolean isValid(String birthDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate birthDateLocalDate;

        try{
            birthDateLocalDate = LocalDate.parse(birthDate, dateTimeFormatter);

        } catch (DateTimeParseException e){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Date format must be dd-MM-yyyy").addConstraintViolation();
            return false;
        }

        return Period.between(birthDateLocalDate, LocalDate.now()).getYears() >= minimumAge;
    }
}
