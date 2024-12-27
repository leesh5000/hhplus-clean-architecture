package kr.co.aerix.hhplus.user.domain.repository;

import kr.co.aerix.hhplus.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long userId);
    Long save(User user);
    void deleteAll();
}
