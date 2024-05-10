package leets.attendance.service;

import leets.attendance.domain.User;
import leets.attendance.dto.UserDTO;
import leets.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserDTO dto){

        validateDuplicateUser(dto);
        validatePasswordInput(dto);

        userRepository.save(User.builder()
                .name(dto.getName())
                .userid(dto.getUserid())
                .partName(dto.getPartName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build());
    }

    //중복 회원 검증
    private void validateDuplicateUser(UserDTO dto) {
        if(userRepository.existsByUserid(dto.getUserid())){
            log.info("User already exists");
            throw new IllegalStateException("User already exists");
        }
    }

    //2차 비밀번호 확인
    private void validatePasswordInput(UserDTO dto) {
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            log.info("Password does not match");
            throw new IllegalStateException("Password does not match");
        }
    }
}
