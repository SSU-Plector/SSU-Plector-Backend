package ssuPlector.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ssuPlector.converter.DeveloperConverter;
import ssuPlector.domain.Developer;
import ssuPlector.dto.request.DeveloperDTO.DeveloperListRequestDTO;
import ssuPlector.dto.request.DeveloperDTO.DeveloperRequestDTO;
import ssuPlector.dto.response.DeveloperDTO.DeveloperDetailDTO;
import ssuPlector.dto.response.DeveloperDTO.DeveloperListResponseDTO;
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

    @Operation(summary = "개발자 프로필 생성", description = "개발자 프로필을 생성합니다._숙희")
    @PatchMapping("")
    public ApiResponse<Long> createDeveloper(
            @RequestParam(value = "email") String email,
            @RequestBody DeveloperRequestDTO requestDTO) {
        Long developerId = developerService.createDeveloper(email, requestDTO);
        return ApiResponse.onSuccess("개발자 프로필 생성 완료.", developerId);
    }

    @Operation(summary = "개발자 상세조회 API", description = "개발자 프로필을 상세조회 합니다._숙희")
    @GetMapping("{developerId}")
    public ApiResponse<DeveloperDetailDTO> getDeveloperDetail(
            @ExistDeveloper @PathVariable("developerId") Long developerId) {
        Developer developer = developerService.getDeveloper(developerId);
        return ApiResponse.onSuccess(
                "개발자 상세조회 완료.", DeveloperConverter.toDeveloperDetailDTO(developer));
    }

    @Operation(summary = "개발자 리스트 조회", description = "개발자 리스트를 조회합니다._찬민")
    @GetMapping("/list")
    public ApiResponse<DeveloperListResponseDTO> getDeveloperList(
            @Valid @ModelAttribute DeveloperListRequestDTO requestDTO,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page) {
        Page<Developer> developerList = developerService.getDeveloperList(requestDTO, page);
        return ApiResponse.onSuccess(
                "개발자 리스트 조회 성공", DeveloperConverter.toDeveloperResponseListDTO(developerList));
    }
}
