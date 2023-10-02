package spring.wantedpreonboardingbackend.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StatusCode {
    //success
    CREATE_POST_SUCCESS(HttpStatus.CREATED.value(), "채용 공고가 등록되었습니다."),
    GET_POST_SUCCESS(HttpStatus.OK.value(), "채용 공고 조회가 완료되었습니다."),
    UPDATE_POST_SUCCESS(HttpStatus.OK.value(), "채용 공고가 수정되었습니다."),
    DELETE_POST_SUCCESS(HttpStatus.OK.value(), "채용 공고가 삭제되었습니다."),
    APPLY_POST_SUCCESS(HttpStatus.OK.value(), "지원이 완료되었습니다."),

    //fail
    NOT_FOUND_COMPANY(HttpStatus.NOT_FOUND.value(), "회사를 찾을 수 없습니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND.value(), "채용 공고가 존재하지 않습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND.value(), "사용자가 존재하지 않습니다."),
    ALREADY_APPLY(HttpStatus.BAD_REQUEST.value(), "이미 지원한 공고입니다.");

    private final int statusCode;
    private final String message;

    StatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
