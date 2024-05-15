package leets.attendance.service;

import jakarta.transaction.Transactional;
import leets.attendance.dto.UserResponseDTO;
import leets.attendance.dto.TokenDTO;
import leets.attendance.entity.Attendances;
import leets.attendance.entity.User;
import leets.attendance.entity.WeekEnum;
import leets.attendance.repository.AttendanceRepository;
import leets.attendance.repository.UserRepository;
import leets.attendance.util.TokenProvider;
import leets.attendance.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public UserResponseDTO signup(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            log.info("User already exists");
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        if (!userRequestDTO.getPassword().equals(userRequestDTO.getConfirmPassword())) {
            log.info("Password does not match");
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        User user = userRequestDTO.toUser(passwordEncoder);
        initializeAttendance(userRequestDTO.getUsername());
        log.info("User {} registered", userRequestDTO.getName());
        return UserResponseDTO.of(userRepository.save(user));
    }

    @Transactional
    public TokenDTO login(UserRequestDTO userRequestDTO) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(), userRequestDTO.getPassword());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(authentication);

        // 4. 토큰 발급
        return tokenDTO;
    }

    public Boolean checkDuplicateId(UserRequestDTO userRequestDTO) {
        return userRepository.existsByUsername(userRequestDTO.getUsername());
    }

    private void initializeAttendance(String username) {
        User user = userRepository.findByUsername(username);

        for (WeekEnum weekEnum : WeekEnum.values()) {
            Attendances attendances = Attendances.builder()
                    .user(user)
                    .date(weekEnum.getDate())
                    .attendance(false)
                    .build();
            attendanceRepository.save(attendances);
        }
        log.info("Attendances initialized");
    }
}
