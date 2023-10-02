package spring.wantedpreonboardingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.wantedpreonboardingbackend.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
