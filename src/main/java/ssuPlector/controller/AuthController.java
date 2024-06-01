package ssuPlector.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ssuPlector.domain.Developer;
import ssuPlector.dto.response.AuthResponseDTO.OAuthResponse;
import ssuPlector.dto.response.AuthResponseDTO.TokenRefreshResponse;
import ssuPlector.global.response.ApiResponse;
import ssuPlector.security.handler.annotation.AuthUser;
import ssuPlector.security.handler.annotation.ExtractToken;
import ssuPlector.service.auth.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth 🛡️", description = "인증 관련 API")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "카카오 로그인", description = "카카오 로그인 합니다._숙희")
    @GetMapping("/kakao/login")
    public ApiResponse<OAuthResponse> kakaoLogin(@RequestParam("code") String code) {
        return ApiResponse.onSuccess("카카오 로그인 완료", authService.KakaoLogin(code));
    }

    @Operation(summary = "카카오 로그아웃", description = "카카오 로그아웃 합니다._숙희")
    @DeleteMapping("/kakao/logout")
    public ApiResponse<String> kakaoLogout(
            @Parameter(name = "developer", hidden = true) @AuthUser Developer developer) {
        authService.KakaoLogout(developer.getId());
        return ApiResponse.onSuccess("카카오 로그아웃 완료");
    }

    @Operation(summary = "토큰 재발급", description = "토큰을 재발급 합니다._숙희")
    @PostMapping("/kakao/refresh")
    public ApiResponse<TokenRefreshResponse> refresh(@ExtractToken String refreshToken) {
        return ApiResponse.onSuccess("토큰 재발급 완료", authService.refresh(refreshToken));
    }

    @Operation(summary = "회원 탈퇴", description = "회원을 비활성화 합니다(soft delete)._현근")
    @DeleteMapping("/withdraw")
    public ApiResponse<String> withdrawDeveloper(
            @Parameter(name = "developer", hidden = true) @AuthUser Developer developer) {
        authService.withdrawDeveloper(developer.getId());
        return ApiResponse.onSuccess("회원 탈퇴 성공");
    }
}
