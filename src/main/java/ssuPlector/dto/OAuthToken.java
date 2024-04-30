package ssuPlector.dto;

import lombok.Data;

@Data
public class OAuthToken {
    String token_type;
    String access_token;
    Integer expires_in;
    String refresh_token;
    Integer refresh_token_expires_in;
    String scope;
}
