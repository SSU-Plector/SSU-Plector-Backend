package ssuPlector.service.developer;

import java.util.List;

import ssuPlector.domain.User;

public interface DeveloperService {
    User getDeveloper(Long id);

    boolean existsByDeveloperId(Long id);

    void updateDeveloperHits(Long developerId, Long hit);

    List<Long> getUpdateTargetDeveloperIds(List<Long> DeveloperIdList);
}
