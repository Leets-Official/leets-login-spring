package leets.attendance.config.jwt;

import leets.attendance.domain.User;
import leets.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public boolean authenticate(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);
        System.out.println(userDetails != null && new BCryptPasswordEncoder().matches(password, userDetails.getPassword()));
        return userDetails != null && new BCryptPasswordEncoder().matches(password, userDetails.getPassword());
    }
}