package ssuPlector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@EnableRedisRepositories
@SpringBootApplication
public class SsuPlectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsuPlectorApplication.class, args);
    }
}
