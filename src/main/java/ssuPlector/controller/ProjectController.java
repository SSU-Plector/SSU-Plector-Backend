package ssuPlector.controller;

import static ssuPlector.dto.request.ProjectDTO.*;

import jakarta.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ssuPlector.converter.ProjectConverter;
import ssuPlector.domain.Project;
import ssuPlector.dto.request.ProjectDTO;
import ssuPlector.dto.response.ProjectDTO.ProjectDetailDTO;
import ssuPlector.global.response.ApiResponse;
import ssuPlector.service.project.ProjectService;
import ssuPlector.validation.annotation.ExistProject;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/projects")
@Tag(name = "Project", description = "프로젝트 관련 API")
public class ProjectController {
    private final ProjectService projectService;

    @Operation(summary = "프로젝트 상세조회 API", description = "프로젝트를 상세조회합니다._숙희")
    @GetMapping("{projectId}")
    public ApiResponse<ProjectDetailDTO> getProjectDetail(
            @ExistProject @PathVariable("projectId") Long projectId) {
        Project project = projectService.getProject(projectId);
        return ApiResponse.onSuccess("프로젝트 상세조회 완료.", ProjectConverter.toProjectDetailDTO(project));
    }

    @Operation(summary = "프로젝트 리스트 조회", description = "프로젝트 리스트 조회합니다._현근")
    @GetMapping("/list")
    public ApiResponse getProjectList(
            @Valid @ModelAttribute ProjectDTO.ProjectListRequestDto requestDto,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page) {
        return ApiResponse.onSuccess(
                "프로젝트 리스트 조회 성공", projectService.getProjectList(requestDto, page));
    }

    @Operation(summary = "프로젝트 생성, 저장 API", description = "프로젝트를 생성 후 저장합니다._찬민")
    @PostMapping
    public ApiResponse createProject(@RequestBody @Valid ProjectRequestDTO requestDTO) {
        Long projectId = projectService.createProject(requestDTO);
        return ApiResponse.onSuccess("프로젝트 생성 및 저장 완료.", projectId);
    }
}
