package ssuPlector.service.project;

import static ssuPlector.dto.request.ProjectDTO.*;

import java.util.List;

import ssuPlector.domain.Project;
import ssuPlector.dto.request.ProjectDTO.ProjectListRequestDto;
import ssuPlector.dto.response.ProjectDTO.ProjectListResponseDto;

public interface ProjectService {

    Long createProject(ProjectRequestDTO requestDTO);

    Project getProject(Long projectId);

    void updateProjectHits(Long projectId, Long hit);

    List<Long> getUpdateTargetProjectIds(List<Long> projectIdList);

    boolean existsByProjectId(Long id);

    ProjectListResponseDto getProjectList(ProjectListRequestDto requestDto, int page);
}
