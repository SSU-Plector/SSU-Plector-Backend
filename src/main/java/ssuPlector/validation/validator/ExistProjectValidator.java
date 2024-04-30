package ssuPlector.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.service.project.ProjectService;
import ssuPlector.validation.annotation.ExistProject;

@Component
@RequiredArgsConstructor
public class ExistProjectValidator implements ConstraintValidator<ExistProject, Long> {
    private final ProjectService projectService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = projectService.existsByProjectId(value);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            GlobalErrorCode.PROJECT_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
