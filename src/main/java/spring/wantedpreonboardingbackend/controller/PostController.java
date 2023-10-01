package spring.wantedpreonboardingbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spring.wantedpreonboardingbackend.dto.PostDto;
import spring.wantedpreonboardingbackend.dto.ResponseDto;
import spring.wantedpreonboardingbackend.dto.StatusCode;
import spring.wantedpreonboardingbackend.service.PostService;

@Tag(name = "Post", description = "지원 공고")
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "지원 공고 등록")
    @PostMapping("/hiring")
    public ResponseEntity<ResponseDto<?>> createPost(@RequestBody PostDto.Req postDto) {
        PostDto.Res dto = postService.createPost(postDto);

        return ResponseDto.toResponseEntity(StatusCode.POST_SUCCESS, dto);
    }
}
