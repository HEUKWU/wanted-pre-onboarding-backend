package spring.wantedpreonboardingbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.wantedpreonboardingbackend.dto.PostDto;
import spring.wantedpreonboardingbackend.dto.ResponseDto;
import spring.wantedpreonboardingbackend.service.PostService;

import java.util.List;

import static spring.wantedpreonboardingbackend.dto.StatusCode.*;

@Tag(name = "Post", description = "채용 공고")
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "채용 공고 등록")
    @PostMapping("/hiring/posting")
    public ResponseEntity<ResponseDto<?>> createPost(@RequestBody PostDto.Req postDto) {
        PostDto.Res dto = postService.createPost(postDto);

        return ResponseDto.toResponseEntity(CREATE_POST_SUCCESS, dto);
    }

    @Operation(summary = "채용 공고 수정")
    @PutMapping("/hiring/editing/{postId}")
    public ResponseEntity<ResponseDto<?>> updatePost(@PathVariable Long postId, @RequestBody PostDto.Update updateDto) {
        PostDto.Res dto = postService.updatePost(postId, updateDto);

        return ResponseDto.toResponseEntity(UPDATE_POST_SUCCESS, dto);
    }

    @Operation(summary = "채용 공고 삭제")
    @DeleteMapping("/hiring/deleting/{postId}")
    public ResponseEntity<ResponseDto<?>> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ResponseDto.toResponseEntity(DELETE_POST_SUCCESS, null);
    }

    @Operation(summary = "공고 목록 조회")
    @GetMapping("/hiring")
    public ResponseEntity<ResponseDto<?>> getPostList(@RequestParam int page, @RequestParam int size) {
        List<PostDto.GetPost> getPost = postService.getPostList(page - 1, size);

        return ResponseDto.toResponseEntity(GET_POST_SUCCESS, getPost);
    }

    @Operation(summary = "공고 상세 조회")
    @GetMapping("/hiring/details/{postId}")
    public ResponseEntity<ResponseDto<?>> getPost(@PathVariable Long postId) {
        PostDto.GetPost getPost = postService.getPost(postId);

        return ResponseDto.toResponseEntity(GET_POST_SUCCESS, getPost);

    }
}
