package leets.attendance.domain.user.application;


import leets.attendance.domain.attendance.application.AttendanceService;
import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.dto.LoginRequest;
import leets.attendance.domain.user.dto.UserRequest;
import leets.attendance.domain.user.dto.UserResponse;
import leets.attendance.domain.user.exception.InvalidIdException;
import leets.attendance.domain.user.exception.InvalidPasswordException;
import leets.attendance.domain.user.exception.ConflictIdException;
import leets.attendance.domain.user.repository.UserRepository;
import leets.attendance.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AttendanceService attendanceService;
    private final TokenProvider tokenProvider;
    public UserResponse register(UserRequest userRequest) throws Exception{
        String id = userRequest.getId();
        if(userRepository.findById(id).isPresent()){
            throw new ConflictIdException();
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

    public UserResponse login(LoginRequest loginRequest) throws Exception{
        String id = loginRequest.getId();
        if(!userRepository.findById(id).isPresent()){
            throw new InvalidIdException();
        }
        String password = loginRequest.getPassword();
        User user = userRepository.findById(id).orElseThrow(Exception::new);
        if(!user.getPassword().equals(password)){
            throw new InvalidPasswordException();
        }
        String token = tokenProvider.createToken(user.getName());
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .part(user.getPart())
                .token(token)
                .build();
    }

    public String checkDuplicateId(String id) throws Exception{
        if(userRepository.findById(id).isPresent()){
            throw new ConflictIdException();
        }
        return id;
    }
}
