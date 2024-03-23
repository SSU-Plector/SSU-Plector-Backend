package ssuPlector.global.response.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    _OK(HttpStatus.OK, "200", "OK");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
