package ssuPlector.service.project;

import java.util.List;

import ssuPlector.domain.Project;

import ssuPlector.dto.requestDto.ProjectListRequestDto;
import ssuPlector.dto.responseDto.ProjectListResponseDto;

public interface ProjectService {
    Project getProject(Long projectId);

    void updateProjectHits(Long projectId, Long hit);

    List<Long> getUpdateTargetProjectIds(List<Long> projectIdList);

    boolean existsByProjectId(Long id);

    ProjectListResponseDto getProjectList(ProjectListRequestDto requestDto, int page);
}
