package spring.wantedpreonboardingbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.wantedpreonboardingbackend.entity.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByDeletedIsFalse(Pageable pageable);

    Optional<Post> findByIdAndDeletedIsFalse(Long postId);
}
