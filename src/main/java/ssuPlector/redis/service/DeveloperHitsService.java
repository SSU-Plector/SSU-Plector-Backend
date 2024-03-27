package ssuPlector.redis.service;

import java.util.List;

public interface DeveloperHitsService {
    void incrementHits(Long userId);

    Long getHits(Long userId);

    List<Long> getAllDeveloperIds();
}
