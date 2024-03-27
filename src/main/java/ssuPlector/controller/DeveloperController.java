package ssuPlector.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ssuPlector.converter.UserConverter;
import ssuPlector.domain.User;
import ssuPlector.dto.response.DeveloperDTO.DeveloperDetailDTO;
import ssuPlector.global.response.ApiResponse;
import ssuPlector.service.developer.DeveloperService;
import ssuPlector.validation.annotation.ExistDeveloper;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/developers")
@Tag(name = "Developer 🖥️", description = "개발자 관련 API")
public class DeveloperController {
    private final DeveloperService developerService;

    @Operation(summary = "개발자 상세조회 API", description = "개발자 프로필을 상세조회 합니다.")
    @GetMapping("{developerId}")
    public ApiResponse<DeveloperDetailDTO> getDeveloperDetail(
            @ExistDeveloper @PathVariable("developerId") Long developerId) {
        User user = developerService.getDeveloper(developerId);
        return ApiResponse.onSuccess("개발자 상세조회 완료.", UserConverter.toDeveloperDetailDTO(user));
    }
}
