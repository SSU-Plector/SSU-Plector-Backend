package ssuPlector.service.developer;

import static ssuPlector.dto.request.DeveloperDTO.*;

import java.util.List;

import org.springframework.data.domain.Page;

import ssuPlector.domain.Developer;

public interface DeveloperService {
    Long createDeveloper(DeveloperRequestDTO requestDTO);

    Developer getDeveloper(Long id);

    boolean existsByDeveloperId(Long id);

    void updateDeveloperHits(Long developerId, Long hit);

    List<Long> getUpdateTargetDeveloperIds(List<Long> DeveloperIdList);

    Page<Developer> getDeveloperList(DeveloperListRequestDTO requestDTO, int page);
}
