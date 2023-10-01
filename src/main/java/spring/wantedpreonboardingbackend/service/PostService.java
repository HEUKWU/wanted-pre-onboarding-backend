package spring.wantedpreonboardingbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.wantedpreonboardingbackend.dto.PostDto;
import spring.wantedpreonboardingbackend.entity.Company;
import spring.wantedpreonboardingbackend.entity.Post;
import spring.wantedpreonboardingbackend.exception.NotFoundCompanyException;
import spring.wantedpreonboardingbackend.exception.NotFoundPostException;
import spring.wantedpreonboardingbackend.repository.CompanyRepository;
import spring.wantedpreonboardingbackend.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<PostDto.PostList> getPostList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAll(pageable);

        if (posts.isEmpty()) {
            throw new NotFoundPostException();
        }

        List<PostDto.PostList> postList = new ArrayList<>();

        for (Post post : posts) {
            postList.add(PostDto.PostList.of(post));
        }

        return postList;
    }
}
