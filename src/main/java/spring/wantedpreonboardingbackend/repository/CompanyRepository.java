package spring.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.wantedpreonboardingbackend.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
