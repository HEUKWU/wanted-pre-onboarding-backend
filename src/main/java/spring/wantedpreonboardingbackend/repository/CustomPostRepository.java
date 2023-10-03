package spring.wantedpreonboardingbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.wantedpreonboardingbackend.entity.Post;
import spring.wantedpreonboardingbackend.entity.Search;

public interface CustomPostRepository {
    Page<Post> findBySearchOption(Pageable pageable, Search search);
}
