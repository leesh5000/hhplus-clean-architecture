package kr.co.aerix.hhplus.user.infrastructure.persistence;

import kr.co.aerix.hhplus.user.domain.User;
import kr.co.aerix.hhplus.user.domain.repository.UserRepository;
import kr.co.aerix.hhplus.user.infrastructure.persistence.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return userJpaRepository.findById(userId);
    }

    @Override
    public Long save(User user) {
        return userJpaRepository.save(user)
                .getId();
    }

    @Override
    public void deleteAll() {
        userJpaRepository.deleteAll();
    }
}
