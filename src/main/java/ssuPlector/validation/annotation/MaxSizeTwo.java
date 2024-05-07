package ssuPlector.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import ssuPlector.validation.validator.MaxSizeTwoValidator;

@Documented
@Constraint(validatedBy = MaxSizeTwoValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSizeTwo {
    String message() default " 최소 1개 최대 2개 범위를 넘었습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
