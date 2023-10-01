package spring.wantedpreonboardingbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import spring.wantedpreonboardingbackend.dto.PostDto;
import spring.wantedpreonboardingbackend.dto.ResponseDto;
import spring.wantedpreonboardingbackend.dto.StatusCode;
import spring.wantedpreonboardingbackend.service.PostService;

import java.util.List;

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

    @Operation(summary = "공고 목록 조회")
    @GetMapping("/hiring")
    public ResponseEntity<ResponseDto<?>> getPostList(@RequestParam int page, @RequestParam int size) {
        List<PostDto.PostList> postList = postService.getPostList(page - 1, size);

        return ResponseDto.toResponseEntity(StatusCode.GET_ALL_POST_SUCCESS, postList);
    }
}
