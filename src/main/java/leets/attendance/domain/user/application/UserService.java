package leets.attendance.domain.user.business;

import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.domain.repository.UserRepository;
import leets.attendance.domain.user.exception.UserAlreadyRegisteredException;
import leets.attendance.domain.user.presentation.dto.Request.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequestDto registerRequestDto){

        //아이디 중복 체크
        if (userRepository.existsByUserId(registerRequestDto.id())) {
            throw new UserAlreadyRegisteredException();
        }

        User user = User.builder()
                .userId(registerRequestDto.id())
                .userPwd(passwordEncoder.encode(registerRequestDto.password()))
                .name(registerRequestDto.name())
                .part(registerRequestDto.part()).build();
        userRepository.save(user);

    }

}
