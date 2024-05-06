package leets.domain.attendance.service;

import leets.domain.attendance.domain.user.User;
import leets.domain.attendance.domain.user.repository.UserRepository;
import leets.global.EncryptHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncryptHelper encryptHelper;

    public User save(User user) {
        String encrypt = encryptHelper.encrypt(user.getPassword());
        User encoded = user.encodePassword(encrypt);
        return userRepository.save(encoded);
    }

    public boolean checkIdDuplication(String joinId) {
        return userRepository.existsByJoinId(joinId);
    }
}
