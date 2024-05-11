package leets.attendance.service;

import leets.attendance.dto.UserDTO;
import leets.attendance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserJoinServiceTest {

    @Autowired
    UserJoinService userJoinService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void 회원가입_테스트() {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setName("test");
        userDTO.setPassword("1234");
        userDTO.setConfirmPassword("1234");
        userDTO.setUserid("test");
        userDTO.setPartName("be");

        //when
        userJoinService.register(userDTO);

        //then
        assertThat((userRepository.findByUserid(userDTO.getUserid())).getUsername()).isEqualTo(userDTO.getUserid());
    }

    @Test
    @Transactional
    void 중복회원_검증_테스트() {
        //given
        UserDTO dto1 = new UserDTO();
        dto1.setName("test");
        dto1.setPassword("1234");
        dto1.setConfirmPassword("1234");
        dto1.setUserid("test");
        dto1.setPartName("be");

        UserDTO dto2 = new UserDTO();
        dto2.setName("test");
        dto2.setPassword("1234");
        dto2.setConfirmPassword("1234");
        dto2.setUserid("test");
        dto2.setPartName("be");

        //when
        userJoinService.register(dto1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
            userJoinService.register(dto2));
        assertThat(e.getMessage()).isEqualTo("User already exists");
    }

    @Test
    @Transactional
    void 비밀번호_확인_테스트(){
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setName("test");
        userDTO.setPassword("1234");
        userDTO.setConfirmPassword("5678");
        userDTO.setUserid("test");
        userDTO.setPartName("be");

        //when
        //2차 비밀번호 입력이 틀리면 테스트 성공

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
            userJoinService.register(userDTO));
        assertThat(e.getMessage()).isEqualTo("Password does not match");
    }
}