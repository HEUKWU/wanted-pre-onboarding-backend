package spring.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.wantedpreonboardingbackend.entity.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {
    Optional<Post> findByIdAndDeletedIsFalse(Long postId);
}
