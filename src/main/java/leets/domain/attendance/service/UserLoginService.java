package leets.domain.attendance.service;

import leets.domain.attendance.domain.user.User;
import leets.domain.attendance.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;

    public User save(User user) {

        return userRepository.save(user);
    }
}
