package leets.attendance.domain.user.api;

import leets.attendance.domain.user.application.UserService;
import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody JoinDto joinDto){
        userService.register(joinDto);
        return ResponseEntity.ok().build();
    }

    //id 중복 확인
    @GetMapping("/check-duplicate-id")
    public ResponseEntity<String> checkDuplicateId(@RequestBody String joinId){
        boolean isDuplicated = userService.checkDuplicateId(joinId);

        if (isDuplicated) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다!");
        } else {
            return ResponseEntity.ok().build();
        }
    }

}
