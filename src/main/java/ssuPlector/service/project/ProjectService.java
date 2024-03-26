package ssuPlector.service.project;

import java.util.List;

import ssuPlector.domain.Project;

public interface ProjectService {
    Project getProject(Long projectId);

    void updateProjectHits(Long projectId, Long hit);

    List<Long> getUpdateTargetProjectIds(List<Long> projectIdList);

    boolean existsByProjectId(Long id);
}
