package spring.wantedpreonboardingbackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.wantedpreonboardingbackend.dto.PostDto;
import spring.wantedpreonboardingbackend.entity.Company;
import spring.wantedpreonboardingbackend.entity.Post;
import spring.wantedpreonboardingbackend.repository.CompanyRepository;
import spring.wantedpreonboardingbackend.repository.PostRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("채용 공고를 추가할 수 있다.")
    void creatPost() {

        //given
        Company company = Company.builder().id(1L).build();
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        given(postRepository.save(any())).willReturn(Post.builder()
                .id(1L)
                .position("백엔드")
                .reward(1000000)
                .description("백엔드를 채용")
                .skill("Spring")
                .build());

        PostDto.Req requestDto = PostDto.Req.builder()
                .companyId(company.getId())
                .position("백엔드")
                .reward(1000000)
                .description("백엔드를 채용")
                .skill("Spring")
                .build();

        //when
        PostDto.Res post = postService.createPost(requestDto);

        //then
        assertThat(post.getPostId()).isEqualTo(1L);
        assertThat(post.getPosition()).isEqualTo("백엔드");
    }

    @Test
    @DisplayName("채용 공고를 수정할 수 있다.")
    void updatePost() {

        //given
        Post post = Post.builder()
                .id(1L)
                .position("백엔드")
                .reward(1000000)
                .description("백엔드를 채용")
                .skill("Spring")
                .build();

        when(postRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(post));

        //when
        PostDto.Update updateDto = PostDto.Update.builder()
                .position("백엔드")
                .reward(1500000)
                .description("백엔드를 채용")
                .skill("Django")
                .build();

        PostDto.Res updatePost = postService.updatePost(1L, updateDto);

        //then
        assertThat(updatePost.getReward()).isEqualTo(1500000);
        assertThat(updatePost.getSkill()).isEqualTo("Django");
    }

    @Test
    @DisplayName("채용 공고를 삭제할 수 있다.")
    void deletePost() {

        //given
        Post post = Post.builder()
                .id(1L)
                .position("백엔드")
                .reward(1000000)
                .description("백엔드를 채용")
                .skill("Spring")
                .build();

        when(postRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(post));

        //when
        postService.deletePost(1L);

        //then
        assertThat(post.isDeleted()).isTrue();
    }
}