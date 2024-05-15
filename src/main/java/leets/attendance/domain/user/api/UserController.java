package leets.attendance.domain.user.api;

import leets.attendance.domain.user.application.UserService;
import leets.attendance.domain.user.domain.User;
import leets.attendance.domain.user.dto.JoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody JoinDto joinDto){
        userService.register(joinDto);
        return ResponseEntity.ok().build();
    }
}
