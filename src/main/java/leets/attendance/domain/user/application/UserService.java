package leets.attendance.domain.user.application;

import leets.attendance.domain.user.dao.UserRepository;
import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void register(JoinDto joinDto) {
        User user = User.builder()
                .joinId(joinDto.getJoinId())
                .password(joinDto.getPassword())
                .name(joinDto.getName())
                .part(joinDto.getPart())
                .build();

        userRepository.save(user);
    }
}
