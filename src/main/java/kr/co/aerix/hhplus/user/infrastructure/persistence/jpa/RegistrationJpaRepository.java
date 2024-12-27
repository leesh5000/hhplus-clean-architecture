package kr.co.aerix.hhplus.user.infrastructure.persistence.jpa;

import kr.co.aerix.hhplus.user.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationJpaRepository extends JpaRepository<Registration, Long> {
    List<Registration> findAllByUserId(Long userId);
}
