package ssuPlector.redis.service;

import java.util.List;

public interface ProjectHitsService {
    void incrementHits(Long projectId);

    Long getHits(Long developerId);

    List<Long> getAllProjectIds();
}
