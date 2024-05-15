package leets.attendance.service;

import leets.attendance.domain.User;
import leets.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    //email으로 사용자의 정보를 가져오는 method
    @Override
    public User loadUserByUsername(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException(email));
    }
}
