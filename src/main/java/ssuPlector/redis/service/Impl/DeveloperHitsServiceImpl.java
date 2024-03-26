package ssuPlector.redis.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.redis.repository.DeveloperHitsRepository;
import ssuPlector.redis.service.DeveloperHitsService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeveloperHitsServiceImpl implements DeveloperHitsService {
    private final DeveloperHitsRepository userHitsRepository;

    @Override
    @Transactional
    public void incrementHits(Long id) {
        String userId = id.toString();
        if (userHitsRepository.getHits(userId).isEmpty()) {
            userHitsRepository.createHits(userId);
        }
        userHitsRepository.incrementHits(userId);
    }

    @Override
    public Long getHits(Long id) {
        String developerId = id.toString();
        String hits = userHitsRepository.getHits(developerId).orElse(null);
        return hits != null ? Long.valueOf(hits) : null;
    }

    @Override
    public List<Long> getAllDeveloperIds() {
        return userHitsRepository.getAllDeveloperIds().stream().map(Long::valueOf).toList();
    }
}
