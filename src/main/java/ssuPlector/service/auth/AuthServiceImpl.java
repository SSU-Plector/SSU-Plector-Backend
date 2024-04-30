package ssuPlector.service.auth;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.converter.AuthConverter;
import ssuPlector.domain.Developer;
import ssuPlector.domain.category.SocialType;
import ssuPlector.dto.KakaoProfile;
import ssuPlector.dto.OAuthToken;
import ssuPlector.dto.response.AuthResponseDTO.OAuthResponse;
import ssuPlector.dto.response.AuthResponseDTO.TokenRefreshResponse;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.service.RefreshTokenService;
import ssuPlector.repository.developer.DeveloperRepository;
import ssuPlector.security.provider.JwtTokenProvider;
import ssuPlector.security.provider.KakaoAuthProvider;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final KakaoAuthProvider kakaoAuthProvider; // code로 access token 받아오기
    private final DeveloperRepository developerRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public OAuthResponse KakaoLogin(String code) {
        OAuthToken oAuthToken = kakaoAuthProvider.getAccessToken(code); // oAuthToken으로 사용자 정보 받아오기
        KakaoProfile kakaoProfile = kakaoAuthProvider.getProfile(oAuthToken.getAccess_token());

        Optional<Developer> developer =
                developerRepository.findByEmailAndSocialType(
                        kakaoProfile.getKakaoAccount().getEmail(), SocialType.kakao);
        Developer newDeveloper;
        boolean isLogin = false;

        if (developer.isEmpty()) {
            newDeveloper = developerRepository.save(AuthConverter.toDeveloper(kakaoProfile));
        } else {
            newDeveloper = developer.get();
            isLogin = true;
        }
        String accessToken = jwtTokenProvider.createAccessToken(newDeveloper.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken(newDeveloper.getId());
        refreshTokenService.saveToken(refreshToken);

        return AuthConverter.toOAuthResponse(accessToken, refreshToken, isLogin, newDeveloper);
    }

    @Override
    public TokenRefreshResponse refresh(String refreshToken) {
        jwtTokenProvider.isValidToken(refreshToken);

        if (!refreshTokenService.isEqualsToken(refreshToken))
            throw new GlobalException(GlobalErrorCode.NOT_EQUAL_TOKEN);

        String newAccessToken =
                jwtTokenProvider.createAccessToken(jwtTokenProvider.getId(refreshToken));
        String newRefreshToken =
                jwtTokenProvider.createRefreshToken(jwtTokenProvider.getId(refreshToken));
        refreshTokenService.saveToken(newRefreshToken);
        return AuthConverter.tokenRefreshResponse(newAccessToken, newRefreshToken);
    }

    @Override
    public void KakaoLogout(Long developerId) {
        refreshTokenService.deleteToken(developerId);
    }
}
