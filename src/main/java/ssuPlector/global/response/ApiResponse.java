package ssuPlector.global.response;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.global.response.code.SuccessCode;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private final T data;

    // 성공 시 응답
    public static <T> ApiResponse<T> onSuccess(String message, T result) {
        return new ApiResponse<>(true, SuccessCode._OK.getCode(), message, result);
    }

    // 성공 시 응답
    public static ApiResponse<?> onSuccess(String message) {
        return new ApiResponse<>(true, SuccessCode._OK.getCode(), message, new HashMap<>());
    }

    // 실패 시  응답
    public static <T> ApiResponse<T> onFailure(GlobalErrorCode globalErrorCode, T result) {
        return new ApiResponse<>(
                false, globalErrorCode.getCode(), globalErrorCode.getMessage(), result);
    }

    // 실패 시 응답
    public static ApiResponse<?> onFailure(GlobalErrorCode globalErrorCode) {
        return new ApiResponse<>(
                false, globalErrorCode.getCode(), globalErrorCode.getMessage(), new HashMap<>());
    }
}
