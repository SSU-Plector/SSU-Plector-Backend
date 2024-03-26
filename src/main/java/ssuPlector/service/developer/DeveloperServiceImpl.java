package ssuPlector.service.developer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.User;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.service.DeveloperHitsService;
import ssuPlector.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final UserRepository userRepository;
    private final DeveloperHitsService developerHitsService;

    @Override
    public User getDeveloper(Long id) {
        User user =
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new GlobalException(GlobalErrorCode.USER_NOT_FOUND));
        developerHitsService.incrementHits(id);
        return user;
    }

    @Override
    public boolean existsByDeveloperId(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public void updateDeveloperHits(Long developerId, Long hit) {
        userRepository.incrementHitsById(developerId, hit);
    }

    @Override
    public List<Long> getUpdateTargetDeveloperIds(List<Long> developerIdList) {
        return userRepository.findAllByIdIn(developerIdList).stream().map(User::getId).toList();
    }
}
