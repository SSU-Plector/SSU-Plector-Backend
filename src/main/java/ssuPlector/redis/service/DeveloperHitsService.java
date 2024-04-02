package ssuPlector.redis.service;

import java.util.List;

public interface DeveloperHitsService {
    void incrementHits(Long developerId);

    Long getHits(Long developerId);

    List<Long> getAllDeveloperIds();
}
