package spring.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.wantedpreonboardingbackend.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
