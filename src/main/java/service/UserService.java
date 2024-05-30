package service;

import domain.User;
import dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserId(registerRequest.getUserId());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setName(registerRequest.getName());
        user.setPart(registerRequest.getPart());

        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserId(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
