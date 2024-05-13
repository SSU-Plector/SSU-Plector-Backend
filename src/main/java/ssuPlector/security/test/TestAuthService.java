package ssuPlector.security.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.Developer;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.repository.developer.DeveloperRepository;
import ssuPlector.security.provider.JwtTokenProvider;
import ssuPlector.security.test.dto.TestAuthResponse;

@Service
@RequiredArgsConstructor
public class TestAuthService {

    private final DeveloperRepository developerRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TestAuthResponse login(String email) {
        Developer developer =
                developerRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new GlobalException(GlobalErrorCode.DEVELOPER_NOT_FOUND));

        String accessToken = jwtTokenProvider.createAccessToken(developer.getId());

        return TestAuthResponse.builder().accessToken(accessToken).build();
    }
}
