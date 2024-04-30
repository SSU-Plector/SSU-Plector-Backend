package ssuPlector.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import ssuPlector.validation.validator.ExistProjectValidator;

@Documented
@Constraint(validatedBy = ExistProjectValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistProject {
    String message() default "존재하지 않는 프로젝트입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
