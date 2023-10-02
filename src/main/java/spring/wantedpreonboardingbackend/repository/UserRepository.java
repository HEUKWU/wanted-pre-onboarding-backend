package spring.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.wantedpreonboardingbackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
