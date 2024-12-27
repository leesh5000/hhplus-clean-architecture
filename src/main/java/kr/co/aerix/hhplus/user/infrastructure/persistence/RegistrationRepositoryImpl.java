package kr.co.aerix.hhplus.user.infrastructure.persistence;

import jakarta.transaction.Transactional;
import kr.co.aerix.hhplus.user.domain.Registration;
import kr.co.aerix.hhplus.user.domain.repository.RegistrationRepository;
import kr.co.aerix.hhplus.user.infrastructure.persistence.jpa.RegistrationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository {

    private final RegistrationJpaRepository registrationJpaRepository;

    @Override
    public void save(Registration registration) {
        registrationJpaRepository.save(registration);
    }

    @Override
    public List<Registration> findAllByUserId(Long userId) {
        return registrationJpaRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteAll() {
        registrationJpaRepository.deleteAll();
    }
}
