package kr.co.aerix.hhplus.mock;

import kr.co.aerix.hhplus.user.domain.User;
import kr.co.aerix.hhplus.user.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class FakeUserRepository implements UserRepository {

    private final AtomicLong autoIncrementedId = new AtomicLong(0);
    private final List<User> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<User> findById(Long userId) {
        return data.stream().filter(item -> item.getId().equals(userId)).findAny();
    }

    public Long save(User user) {
        if (user.getId() != null) {
            data.removeIf(item -> item.getId().equals(user.getId()));
        }
        User newUser = user.copy(autoIncrementedId.incrementAndGet());
        data.add(newUser);
        return newUser.getId();
    }

    @Override
    public void deleteAll() {
        data.clear();
    }


}
