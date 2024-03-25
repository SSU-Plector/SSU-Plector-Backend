package ssuPlector.service.developer;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.User;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final UserRepository userRepository;

    @Override
    public User getDeveloper(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.USER_NOT_FOUND));
    }

    @Override
    public boolean existsByDeveloperId(Long id) {
        return userRepository.existsById(id);
    }
}
