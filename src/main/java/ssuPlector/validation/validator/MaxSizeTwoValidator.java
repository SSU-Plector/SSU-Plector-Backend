package ssuPlector.validation.validator;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.validation.annotation.MaxSizeTwo;

@Component
public class MaxSizeTwoValidator implements ConstraintValidator<MaxSizeTwo, List<?>> {
    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        boolean isValid = value.size() <= 2 && value.size() > 0;
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            GlobalErrorCode.ESCAPE_MAX_LIST_SIZE.toString())
                    .addConstraintViolation();
        }
        return isValid;
    }
}
