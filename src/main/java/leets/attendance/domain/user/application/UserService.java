package leets.attendance.domain.user.application;


import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.dto.UserRequest;
import leets.attendance.domain.user.dto.UserResponse;
import leets.attendance.domain.user.exception.UserConflictException;
import leets.attendance.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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

        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .part(user.getPart())
                .build();
    }
}
