package ssuPlector.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ssuPlector.global.response.ApiResponse;
import ssuPlector.service.projectDeveloper.ProjectDeveloperService;
import ssuPlector.validation.annotation.ExistProject;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projectDeveloper")
@Tag(name = "Project-Developer API", description = "프로젝트 개발자 관련 API")
public class ProjectDeveloperController {
    private final ProjectDeveloperService projectDeveloperService;

    @Operation(summary = "개발자-프로젝트 연결 API", description = "개발자와 프로젝트를 연결합니다._숙희")
    @PostMapping("/connect")
    public ApiResponse<Long> connectDeveloperProject(
            @ExistProject @RequestParam("projectId") Long projectId) {
        projectDeveloperService.connectProjectDeveloper(projectId);
        return ApiResponse.onSuccess("개발자-프로젝트 연결 완료", projectId);
    }
}
