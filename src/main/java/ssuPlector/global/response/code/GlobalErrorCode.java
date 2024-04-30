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
    _NOT_FOUND(HttpStatus.NOT_FOUND, "404", "요청한 데이터를 찾을 수 없습니다"),

    // DEVELOPER 관련
    DEVELOPER_NOT_FOUND(HttpStatus.NOT_FOUND, "DEVELOPER4001", "해당 유저를 찾을 수 없습니다"),
    DEVELOPER_DUPLICATE(HttpStatus.BAD_REQUEST, "DEVELOPER4002", "이미 등록된 개발자입니다"),

    // PROJECT 관련
    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "PROJECT4001", "해당 프로젝트를 찾을 수 없습니다"),
    // CATEGORY 관련
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY4001", "해당 카테고리를 찾을 수 없습니다"),
    DEV_LANGUAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "DEVLANGUAGE4001", "해당 개발 언어를 찾을 수 없습니다"),
    DEV_TOOLS_NOT_FOUND(HttpStatus.NOT_FOUND, "DEVTOOLS4001", "해당 개발 툴을 찾을 수 없습니다"),
    PART_NOT_FOUND(HttpStatus.NOT_FOUND, "PART4001", "해당 개발 파트를 찾을 수 없습니다"),
    TECH_STACK_NOT_FOUND(HttpStatus.NOT_FOUND, "TECHSTACK4001", "해당 기술 스택을 찾을 수 없습니다"),

    // size관련
    ESCAPE_MAX_LIST_SIZE(HttpStatus.NOT_FOUND, "LISTSIZE4001", "리스트 사이즈를 벗어났습니다.");
    private final HttpStatus httpStatus;

    private final String code;
    private final String message;
}
