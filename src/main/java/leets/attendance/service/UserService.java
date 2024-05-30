package leets.attendance.service;

import leets.attendance.domain.User;
import leets.attendance.dto.AddUserRequest;
import leets.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User save(AddUserRequest dto) {
        User created = dto.toEntity();
        if (created.getEmail() == null || created.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String password = created.getPassword() != null ? created.getPassword() : "";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        created.setPassword(encoder.encode(password));
        return userRepository.save(created);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}