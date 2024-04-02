package ssuPlector.service.developer;

import java.util.List;

import ssuPlector.domain.Developer;

public interface DeveloperService {
    Developer getDeveloper(Long id);

    boolean existsByDeveloperId(Long id);

    void updateDeveloperHits(Long developerId, Long hit);

    List<Long> getUpdateTargetDeveloperIds(List<Long> DeveloperIdList);
}
