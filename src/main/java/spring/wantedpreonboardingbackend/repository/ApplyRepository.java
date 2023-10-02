package spring.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.wantedpreonboardingbackend.entity.Apply;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Optional<Apply> findByUserIdAndPostId(Long userId, Long postId);
}
