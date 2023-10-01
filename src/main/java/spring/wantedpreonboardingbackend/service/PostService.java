package spring.wantedpreonboardingbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.wantedpreonboardingbackend.dto.PostDto;
import spring.wantedpreonboardingbackend.entity.Company;
import spring.wantedpreonboardingbackend.entity.Post;
import spring.wantedpreonboardingbackend.exception.NotFoundCompanyException;
import spring.wantedpreonboardingbackend.repository.CompanyRepository;
import spring.wantedpreonboardingbackend.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CompanyRepository companyRepository;

    public PostDto.Res createPost(PostDto.Req postDto) {
        Company company = companyRepository.findById(postDto.getCompanyId()).orElseThrow(NotFoundCompanyException::new);
        Post post = new Post(company, postDto);
        postRepository.save(post);

        return PostDto.Res.of(company.getId(), post);
    }
}
