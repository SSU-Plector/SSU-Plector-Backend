package ssuPlector.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import ssuPlector.dto.requestDto.ProjectListRequestDto;
import ssuPlector.global.response.ApiResponse;
import ssuPlector.service.project.ProjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Operation(summary = "프로젝트 리스트 조회")
    @GetMapping("/list")
    public ApiResponse getSubject(
            @Valid @RequestBody ProjectListRequestDto requestDto,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page) {
        return ApiResponse.onSuccess(
                "프로젝트 리스트 조회 성공", projectService.getProjectList(requestDto, page));
    }
}
