package spring.wantedpreonboardingbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        return PostDto.Res.of(post);
    }

    @Transactional
    public PostDto.Res updatePost(Long postId, PostDto.Update updateDto) {
        Post post = postRepository.findById(postId).orElseThrow(NotFoundPostException::new);
        Post updatePost = post.update(updateDto);

        return PostDto.Res.of(updatePost);
    }

    @Transactional(readOnly = true)
    public List<PostDto.GetPost> getPostList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAll(pageable);

        return getGetPosts(posts);
    }

    @Transactional(readOnly = true)
    public PostDto.GetPost getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(NotFoundPostException::new);

        return PostDto.GetPost.getPost(post);
    }

    private static List<PostDto.GetPost> getGetPosts(Page<Post> posts) {
        if (posts.isEmpty()) {
            throw new NotFoundPostException();
        }

        List<PostDto.GetPost> getPost = new ArrayList<>();

        for (Post post : posts) {
            getPost.add(PostDto.GetPost.getPostList(post));
        }

        return getPost;
    }
}
