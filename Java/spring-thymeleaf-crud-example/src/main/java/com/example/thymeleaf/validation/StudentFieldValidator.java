package com.example.thymeleaf.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentFieldValidator implements ConstraintValidator<StudentField, String> {

    private StudentFieldType type;

    @Override
    public void initialize(StudentField annotation) {
        this.type = annotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (type.isValid(value)) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(type.getMessage()).addConstraintViolation();
        return false;
    }
}
