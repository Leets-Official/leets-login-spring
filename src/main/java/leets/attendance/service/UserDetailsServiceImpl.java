package leets.attendance.service;

import leets.attendance.domain.User;
import leets.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUserid(username);

        if (foundUser == null) {
            log.error("User not found");
            //스프링 시큐리티가 알아서 예외를 처리해주기 때문에 따로 구현하지 않아도됨
            throw new UsernameNotFoundException("user not found");
        }
        return foundUser;
    }
}
