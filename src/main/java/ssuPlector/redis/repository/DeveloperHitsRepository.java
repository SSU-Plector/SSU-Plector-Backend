package ssuPlector.redis.repository;

import java.util.List;
import java.util.Optional;

public interface DeveloperHitsRepository {
    void incrementHits(String key);

    void createHits(String key);

    Optional<String> getHits(String key);

    List<String> getAllDeveloperIds();
}
