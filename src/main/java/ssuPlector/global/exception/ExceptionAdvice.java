package ssuPlector.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import ssuPlector.global.response.ApiResponse;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    // GlobalException 예외 처리 핸들러
    @ExceptionHandler(value = {GlobalException.class})
    protected ApiResponse handleGlobalException(GlobalException e) {
        log.error(e.getGlobalErrorCode() + ": " + e.getMessage());
        return ApiResponse.onFailure(e.getGlobalErrorCode());
    }

    /** MethodArgumentNotValidException 등의 추가 예외 처리 핸들러 작성 필요 */
}
