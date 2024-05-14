package ssuPlector.service.developer;

import static ssuPlector.dto.request.DeveloperDTO.*;

import java.util.List;

import org.springframework.data.domain.Page;

import ssuPlector.domain.Developer;

public interface DeveloperService {
    Long createDeveloper(String email, DeveloperRequestDTO requestDTO);

    Long updateDeveloper(Long id, DeveloperUpdateRequestDTO requestDTO);

    Developer getDeveloper(Long id, boolean isHit);

    boolean existsByDeveloperId(Long id);

    void updateDeveloperHits(Long developerId, Long hit);

    List<Long> getUpdateTargetDeveloperIds(List<Long> DeveloperIdList);

    Page<Developer> getDeveloperList(DeveloperListRequestDTO requestDTO, int page);
}
