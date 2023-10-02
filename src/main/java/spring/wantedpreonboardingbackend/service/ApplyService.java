package spring.wantedpreonboardingbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.wantedpreonboardingbackend.dto.ApplyDto;
import spring.wantedpreonboardingbackend.entity.Apply;
import spring.wantedpreonboardingbackend.entity.Post;
import spring.wantedpreonboardingbackend.entity.User;
import spring.wantedpreonboardingbackend.exception.AlreadyAppliedException;
import spring.wantedpreonboardingbackend.exception.NotFoundPostException;
import spring.wantedpreonboardingbackend.exception.NotFoundUserException;
import spring.wantedpreonboardingbackend.repository.ApplyRepository;
import spring.wantedpreonboardingbackend.repository.PostRepository;
import spring.wantedpreonboardingbackend.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ApplyRepository applyRepository;

    public ApplyDto.Res apply(ApplyDto.Req requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(NotFoundUserException::new);
        Post post = postRepository.findByIdAndDeletedIsFalse(requestDto.getPostId()).orElseThrow(NotFoundPostException::new);

        Optional<Apply> findApply = applyRepository.findByUserIdAndPostId(user.getId(), post.getId());
        if (findApply.isPresent()) {
            throw new AlreadyAppliedException();
        }

        Apply apply = applyRepository.save(Apply.of(user, post));

        return ApplyDto.Res.of(apply);
    }
}
