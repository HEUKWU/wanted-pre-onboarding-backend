package spring.wantedpreonboardingbackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.wantedpreonboardingbackend.dto.ResponseDto;
import spring.wantedpreonboardingbackend.dto.StatusCode;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundCompanyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ResponseDto<?>> notFoundCompanyException(NotFoundCompanyException e) {
        log.error("Handle company exception : ", e);

        return ResponseDto.toResponseEntity(StatusCode.NOT_FOUND_COMPANY, null);
    }

    @ExceptionHandler(value = NotFoundPostException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ResponseDto<?>> notFoundCompanyException(NotFoundPostException e) {
        log.error("Handle post exception : ", e);

        return ResponseDto.toResponseEntity(StatusCode.NOT_FOUND_POST, null);
    }

    @ExceptionHandler(value = NotFoundUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ResponseDto<?>> notFoundUserException(NotFoundUserException e) {
        log.error("Handle user exception : ", e);

        return ResponseDto.toResponseEntity(StatusCode.NOT_FOUND_USER, null);
    }

    @ExceptionHandler(value = AlreadyAppliedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ResponseDto<?>> alreadyAppliedException(AlreadyAppliedException e) {
        log.error("Handle apply exception : ", e);

        return ResponseDto.toResponseEntity(StatusCode.ALREADY_APPLY, null);
    }
}
