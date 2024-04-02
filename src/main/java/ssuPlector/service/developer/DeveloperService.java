package ssuPlector.service.developer;

import static ssuPlector.dto.request.DeveloperDTO.*;

import java.util.List;

import ssuPlector.domain.Developer;

public interface DeveloperService {
    Long createDeveloper(DeveloperRequestDTO requestDTO);

    Developer getDeveloper(Long id);

    boolean existsByDeveloperId(Long id);

    void updateDeveloperHits(Long developerId, Long hit);

    List<Long> getUpdateTargetDeveloperIds(List<Long> DeveloperIdList);
}
