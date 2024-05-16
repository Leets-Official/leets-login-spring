package leets.attendance.service;

import leets.attendance.domain.Attendances;
import leets.attendance.domain.User;
import leets.attendance.domain.WeekEnum;
import leets.attendance.dto.UserDTO;
import leets.attendance.repository.AttendanceRepository;
import leets.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserJoinService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AttendanceRepository attendanceRepository;

    public void register(UserDTO dto){

        //중복 회원 검증
        validateDuplicateUser(dto.getUserid());
        //비밀번호 2차 확인
        validatePasswordInput(dto);

        userRepository.save(User.builder()
                .name(dto.getName())
                .userid(dto.getUserid())
                .partName(dto.getPartName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build());
        log.info("User {} registered", dto.getName());

        initializeAttendance(dto.getUserid());
    }

    //중복 회원 검증
    public void validateDuplicateUser(String userId) {
        if(userRepository.existsByUserid(userId)){
            log.info("User already exists");
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //2차 비밀번호 확인
    private void validatePasswordInput(UserDTO dto) {
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            log.info("Password does not match");
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }

    private void initializeAttendance(String userid) {
        User user = userRepository.findByUserid(userid);

        for(WeekEnum weekEnum: WeekEnum.values()){
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
