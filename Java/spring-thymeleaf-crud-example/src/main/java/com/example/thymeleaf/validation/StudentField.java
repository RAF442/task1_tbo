package com.example.thymeleaf.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = StudentFieldValidator.class)
public @interface StudentField {

    StudentFieldType value();

    String message() default "Niepoprawna wartość pola.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
