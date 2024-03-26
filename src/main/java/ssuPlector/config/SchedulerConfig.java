package ssuPlector.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssuPlector.redis.service.DeveloperHitsService;
import ssuPlector.service.developer.DeveloperService;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfig {
    private final DeveloperHitsService developerHitsService;
    private final DeveloperService developerService;

    @Scheduled(cron = "0 */10 * * * *") // 10분 마다 수행
    public void updateFlowViewCount() {

        List<Long> developerIdList = developerHitsService.getAllDeveloperIds();
        List<Long> targetDeveloperIdList =
                developerService.getUpdateTargetDeveloperIds(developerIdList);
        log.info(
                "업데이트 대상 Developer: {}",
                targetDeveloperIdList.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));

        targetDeveloperIdList.forEach(
                id -> {
                    Long hits = developerHitsService.getHits(id);
                    developerService.updateDeveloperHits(id, hits);
                });

        log.info("Developer 조회수 업데이트 완료");
    }
}
