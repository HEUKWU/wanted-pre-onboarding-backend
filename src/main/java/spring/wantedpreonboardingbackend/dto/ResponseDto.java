package spring.wantedpreonboardingbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class ResponseDto<T> {

    private int code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static ResponseEntity<ResponseDto<?>> toResponseEntity(StatusCode code, Object data) {
        return ResponseEntity
                .status(code.getStatusCode())
                .body(ResponseDto.builder()
                        .code(code.getStatusCode())
                        .message(code.getMessage())
                        .data(data)
                        .build()
                );
    }
}
