package kr.co.aerix.hhplus.user.domain.repository;

import kr.co.aerix.hhplus.user.domain.Registration;

import java.util.List;

public interface RegistrationRepository {
    void save(Registration registration);
    List<Registration> findAllByUserId(Long userId);
    void deleteAll();
}
