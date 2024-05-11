package leets.attendance.domain.user.application;


import leets.attendance.domain.attendance.Repository.AttendanceRepository;
import leets.attendance.domain.attendance.application.AttendanceService;
import leets.attendance.domain.attendance.domain.Attendance;
import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.dto.UserRequest;
import leets.attendance.domain.user.dto.UserResponse;
import leets.attendance.domain.user.exception.UserConflictException;
import leets.attendance.domain.user.repository.UserRepository;
import leets.attendance.global.DateEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AttendanceService attendanceService;
    public UserResponse register(UserRequest userRequest) throws Exception{
        String id = userRequest.getId();
        if(userRepository.findById(id).isPresent()){
            throw new UserConflictException();
        }
        User user = User.builder()
                .id(id)
                .name(userRequest.getName())
                .part(userRequest.getPart())
                .password(userRequest.getPassword())
                .build();
        userRepository.save(user);
        attendanceService.createInitialAttendance(user);
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .part(user.getPart())
                .build();
    }
}
