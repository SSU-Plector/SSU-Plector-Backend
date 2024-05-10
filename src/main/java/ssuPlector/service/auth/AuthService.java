package ssuPlector.service.auth;

import ssuPlector.dto.response.AuthResponseDTO.OAuthResponse;
import ssuPlector.dto.response.AuthResponseDTO.TokenRefreshResponse;

public interface AuthService {
    OAuthResponse KakaoLogin(String code);

    TokenRefreshResponse refresh(String refreshToken);

    void KakaoLogout(Long developerId);
}
