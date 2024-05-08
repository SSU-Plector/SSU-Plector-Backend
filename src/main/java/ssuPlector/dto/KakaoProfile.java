package ssuPlector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfile {
    private Properties properties;
    private KakaoAccount kakao_account;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Properties {
        private String nickname;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class KakaoAccount {
        private String name;
        private String email;
        private String imageUrl;
    }
}
