package ssuPlector.redis.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.redis.repository.ProjectHitsRepository;
import ssuPlector.redis.service.ProjectHitsService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectHitsServiceImpl implements ProjectHitsService {
    private final ProjectHitsRepository projectHitsRepository;

    @Override
    @Transactional
    public void incrementHits(Long projectId) {
        String id = projectId.toString();
        if (projectHitsRepository.getHits(id).isEmpty()) {
            projectHitsRepository.createHits(id);
        }
        projectHitsRepository.incrementHits(id);
    }

    @Override
    public Long getHits(Long projectId) {
        String id = projectId.toString();
        String hits = projectHitsRepository.getHits(id).orElse(null);
        return hits != null ? Long.valueOf(hits) : null;
    }

    @Override
    public List<Long> getAllProjectIds() {
        return projectHitsRepository.getAllProjectIds().stream().map(Long::valueOf).toList();
    }
}
