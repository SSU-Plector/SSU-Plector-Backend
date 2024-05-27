package ssuPlector.redis.repository.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ssuPlector.redis.repository.DeveloperHitsRepository;

@Repository
@RequiredArgsConstructor
public class DeveloperHitsRepositoryImpl implements DeveloperHitsRepository {
    private final StringRedisTemplate redisTemplate;
    private final String PREFIX = "developerHits";

    @Override
    public void incrementHits(String key) {
        redisTemplate.opsForValue().increment(PREFIX + ":" + key);
    }

    @Override
    public void createHits(String key) {
        redisTemplate.opsForValue().set(PREFIX + ":" + key, "0", 30, TimeUnit.MINUTES);
    }

    @Override
    public Optional<String> getHits(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(PREFIX + ":" + key));
    }

    @Override
    public List<String> getAllDeveloperIds() {
        ScanOptions scanOptions =
                ScanOptions.scanOptions().match(PREFIX + ":" + "*").count(100).build();
        Cursor<String> cursor = redisTemplate.scan(scanOptions);

        List<String> keys = new ArrayList<>();
        while (cursor.hasNext()) {
            keys.add(cursor.next());
        }

        return keys.stream().map(key -> key.split(":")[1]).toList();
    }
}
