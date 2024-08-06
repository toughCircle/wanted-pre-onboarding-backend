package toughcircle.wanted_pre_onboarding_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toughcircle.wanted_pre_onboarding_backend.model.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
