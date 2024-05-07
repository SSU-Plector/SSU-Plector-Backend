package ssuPlector.security.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TestAuthResponse {
    private String accessToken;
    private String refreshToken;
}
