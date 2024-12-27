package kr.co.aerix.hhplus.user.infrastructure.persistence.jpa;

import kr.co.aerix.hhplus.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
