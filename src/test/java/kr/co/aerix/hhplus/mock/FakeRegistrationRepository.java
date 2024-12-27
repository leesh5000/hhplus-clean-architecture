package kr.co.aerix.hhplus.mock;

import kr.co.aerix.hhplus.user.domain.Registration;
import kr.co.aerix.hhplus.user.domain.repository.RegistrationRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class FakeRegistrationRepository implements RegistrationRepository {

    private final AtomicLong autoIncrementedId = new AtomicLong(0);
    private final List<Registration> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(Registration registration) {
        if (registration.getId() != null) {
            data.removeIf(item -> item.getId().equals(registration.getId()));
        }
        Registration newRegistration = registration.copy(autoIncrementedId.incrementAndGet());
        data.add(newRegistration);
    }

    @Override
    public List<Registration> findAllByUserId(Long userId) {
        return data.stream().filter(item -> item.getUserId().equals(userId)).toList();
    }

    @Override
    public void deleteAll() {
        data.clear();
    }
}
