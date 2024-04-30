package ssuPlector.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.service.developer.DeveloperService;
import ssuPlector.validation.annotation.ExistDeveloper;

@Component
@RequiredArgsConstructor
public class ExistDeveloperValidator implements ConstraintValidator<ExistDeveloper, Long> {

    private final DeveloperService developerService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = developerService.existsByDeveloperId(value);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            GlobalErrorCode.DEVELOPER_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
