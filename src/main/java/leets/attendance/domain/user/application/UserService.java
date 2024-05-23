package leets.attendance.domain.user.application;

import jdk.swing.interop.SwingInterOpUtils;
import leets.attendance.domain.user.dao.UserRepository;
import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public boolean register(JoinDto joinDto) {

        //비밀번호, 비밀번호 입력 확인이 일치 && 중복되지 않은 아이디인 경우에만 회원가입
        if(joinDto.getPassword().equals(joinDto.getCheckPassword()) && !checkDuplicateId(joinDto.getJoinId())){
            User user = User.builder()
                    .joinId(joinDto.getJoinId())
                    .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                    .name(joinDto.getName())
                    .part(joinDto.getPart())
                    .build();

            userRepository.save(user);

            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkDuplicateId(String joinId) {
        return userRepository.existsByJoinId(joinId);
    }

}
