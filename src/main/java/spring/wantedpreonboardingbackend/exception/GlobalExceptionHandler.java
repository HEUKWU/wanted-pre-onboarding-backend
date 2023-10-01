package spring.wantedpreonboardingbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.wantedpreonboardingbackend.dto.ResponseDto;
import spring.wantedpreonboardingbackend.dto.StatusCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundCompanyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ResponseDto<?>> notFoundCompanyException(NotFoundCompanyException e) {
        return ResponseDto.toResponseEntity(StatusCode.NOT_FOUND_COMPANY, null);
    }
}
