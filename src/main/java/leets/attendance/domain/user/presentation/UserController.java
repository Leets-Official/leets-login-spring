package leets.attendance.domain.user.presentation;

import leets.attendance.domain.user.application.UserService;
import leets.attendance.domain.user.dto.LoginRequest;
import leets.attendance.domain.user.dto.UserRequest;
import leets.attendance.domain.user.dto.UserResponse;
import leets.attendance.global.dto.ResponseDto;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static leets.attendance.domain.user.presentation.UserResponseMessage.*;

@RestController
@EnableJpaAuditing
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseDto<UserResponse> login(@RequestBody LoginRequest loginRequest) throws Exception{
        return ResponseDto.of(OK.value(),SUCCESS_LOGIN.getMessage(), userService.login(loginRequest));
    }

    @PostMapping(value = "/register")
    public ResponseDto<UserResponse> register(@RequestBody UserRequest userRequest) throws Exception{
        return ResponseDto.of(OK.value(),SUCCESS_REGISTER.getMessage(), userService.register(userRequest));
    }

    @GetMapping(value = "/check-duplicate-id")
    public ResponseDto<String> checkDuplicateId(@RequestBody Map<String,String> id) throws Exception{
        System.out.println(id);
        return ResponseDto.of(OK.value(),USABLE_ID.getMessage(),userService.checkDuplicateId(id.get("id")));
    }
}
