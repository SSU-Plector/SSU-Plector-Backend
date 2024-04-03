package ssuPlector.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import ssuPlector.validation.validator.MaxSizeUnderFourValidator;

@Documented
@Constraint(validatedBy = MaxSizeUnderFourValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSizeUnderFour {
    String message() default " 최소 0개 최대 3개 범위를 넘었습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
