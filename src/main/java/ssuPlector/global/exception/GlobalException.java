package ssuPlector.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ssuPlector.global.response.code.GlobalErrorCode;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private GlobalErrorCode globalErrorCode;
}
