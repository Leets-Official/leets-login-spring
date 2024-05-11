package leets.attendance.service;

import leets.attendance.domain.User;
import leets.attendance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserDetailsServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    //회원 하나를 저장하고 loadByUsername에서 가져온 회원과 동일한지 확인
    @Test
    @Transactional
    void 회원조회_테스트() {
        //given
        User user = User.builder()
                .userid("test")
                .password("1234")
                .name("test")
                .partName("be")
                .build();
        //when
        userRepository.save(user);
        //then
        assertThat(userDetailsService.loadUserByUsername(user.getUsername())).isEqualTo(user);
    }

    //없는 회원을 검색하면 예외를 제대로 반환하는지 확인
    @Test
    @Transactional
    void 빈_회원조회_테스트() {
        //given
        User user = User.builder()
                .userid("test")
                .password("1234")
                .name("test")
                .partName("be")
                .build();
        //when
        userRepository.save(user);
        //then
        UsernameNotFoundException e = assertThrows(UsernameNotFoundException.class , () ->
                userDetailsService.loadUserByUsername("unValidUserName"));
        assertThat(e.getMessage()).isEqualTo("User not found");
    }
}