package ssuPlector.redis.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.domain.RefreshToken;
import ssuPlector.redis.repository.RefreshTokenRepository;
import ssuPlector.security.provider.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public boolean isEqualsToken(String refreshToken) {
        RefreshToken savedRefreshToken =
                refreshTokenRepository
                        .findById(jwtTokenProvider.getId(refreshToken))
                        .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_CONTAIN_TOKEN));
        return savedRefreshToken.getToken().equals(refreshToken);
    }

    @Transactional
    public void saveToken(String refreshToken) {
        RefreshToken newRefreshToken =
                refreshTokenRepository.save(
                        RefreshToken.builder()
                                .id(jwtTokenProvider.getId(refreshToken))
                                .token(refreshToken)
                                .build());
        refreshTokenRepository.save(newRefreshToken);
    }

    @Transactional
    public void deleteToken(Long developerId) {
        RefreshToken deleteRefreshToken =
                refreshTokenRepository
                        .findById(developerId)
                        .orElseThrow(() -> new GlobalException(GlobalErrorCode.NOT_CONTAIN_TOKEN));
        refreshTokenRepository.delete(deleteRefreshToken);
    }
}
