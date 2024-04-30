package ssuPlector.global.response.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 에러, 관리자에게 문의 바립니다"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "요청 형식이 잘못되었습니다"),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "사용자 인증에 실패했습니다"),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "403", "접근 권한이 없는 요청입니다"),
    _NOT_FOUND(HttpStatus.NOT_FOUND, "404", "요청한 데이터를 찾을 수 없습니다");

    /** 코드 작성에 따라 추가 예외 코드 작성 필요 */
    private final HttpStatus httpStatus;

    private final String code;
    private final String message;
}
