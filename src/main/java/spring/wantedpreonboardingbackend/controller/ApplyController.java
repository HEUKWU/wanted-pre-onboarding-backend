package spring.wantedpreonboardingbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spring.wantedpreonboardingbackend.dto.ApplyDto;
import spring.wantedpreonboardingbackend.dto.ResponseDto;
import spring.wantedpreonboardingbackend.dto.StatusCode;
import spring.wantedpreonboardingbackend.service.ApplyService;

@Tag(name = "Apply", description = "지원")
@Controller
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @Operation(summary = "채용 공고 지원")
    @PostMapping("/hiring/applying")
    public ResponseEntity<ResponseDto<?>> apply(@RequestBody ApplyDto.Req requestDto) {
        ApplyDto.Res responseDto = applyService.apply(requestDto);

        return ResponseDto.toResponseEntity(StatusCode.APPLY_POST_SUCCESS, responseDto);
    }
}
