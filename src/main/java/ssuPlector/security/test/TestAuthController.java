package ssuPlector.security.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ssuPlector.global.response.ApiResponse;
import ssuPlector.security.test.dto.TestAuthResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Test Auth", description = "인증/인가 테스트 용")
public class TestAuthController {

    private final TestAuthService testAuthService;

    @PostMapping("/login")
    public ApiResponse<TestAuthResponse> login(@RequestParam(value = "email") String email) {
        return ApiResponse.onSuccess("로그인 테스트 완료", testAuthService.login(email));
    }
}
