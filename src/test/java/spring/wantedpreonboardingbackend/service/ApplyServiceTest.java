package spring.wantedpreonboardingbackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.wantedpreonboardingbackend.dto.ApplyDto;
import spring.wantedpreonboardingbackend.entity.Apply;
import spring.wantedpreonboardingbackend.entity.Company;
import spring.wantedpreonboardingbackend.entity.Post;
import spring.wantedpreonboardingbackend.entity.User;
import spring.wantedpreonboardingbackend.exception.AlreadyAppliedException;
import spring.wantedpreonboardingbackend.repository.ApplyRepository;
import spring.wantedpreonboardingbackend.repository.PostRepository;
import spring.wantedpreonboardingbackend.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplyServiceTest {

    @InjectMocks
    private ApplyService applyService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplyRepository applyRepository;

    @Test
    @DisplayName("사용자는 채용 공고에 지원할 수 있다.")
    void apply() {

        //given
        User user = User.builder()
                .id(1L)
                .build();

        Post post = Post.builder()
                .id(1L)
                .company(Company.builder().companyName("원티드랩").build())
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.findByIdAndDeletedIsFalse(1L)).thenReturn(Optional.of(post));
        given(applyRepository.save(any())).willReturn(Apply.of(user, post));

        //when
        ApplyDto.Req requestDto = ApplyDto.Req.builder()
                .userId(user.getId())
                .postId(post.getId())
                .build();

        ApplyDto.Res apply = applyService.apply(requestDto);

        assertThat(apply.getUserId()).isEqualTo(1L);
        assertThat(apply.getPostId()).isEqualTo(1L);
        assertThat(apply.getCompanyName()).isEqualTo("원티드랩");
    }

    @Test
    @DisplayName("사용자는 하나의 채용 공고에 1회만 지원할 수 있다.")
    void duplicateApply_X() {

        //given
        User user = User.builder()
                .id(1L)
                .build();

        Post post = Post.builder()
                .id(1L)
                .company(Company.builder().build())
                .build();

        //when
        //then
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(postRepository.findByIdAndDeletedIsFalse(user.getId())).thenReturn(Optional.of(post));
        when(applyRepository.findByUserIdAndPostId(1L, 1L)).thenReturn(Optional.of(Apply.of(user, post)));

        assertThrows(AlreadyAppliedException.class, () -> {
            applyService.apply(ApplyDto.Req.builder()
                    .userId(1L)
                    .postId(1L)
                    .build());
        });
    }
}