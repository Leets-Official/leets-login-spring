package leets.attendance.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leets.attendance.config.jwt.AuthService;
import leets.attendance.domain.User;
import leets.attendance.dto.AddUserRequest;
import leets.attendance.dto.CreateAccessTokenResponse;
import leets.attendance.dto.LoginRequest;
import leets.attendance.service.TokenService;
import leets.attendance.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Slf4j //able to leave logs
@RestController
public class UserApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthService authService;

    @PostMapping("/users/register")
    public ResponseEntity<User> create(@RequestBody AddUserRequest request) {
        System.out.println("Received request: " + request.toString());
        User created = userService.save(request);

        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/users/login")
    public ResponseEntity<CreateAccessTokenResponse> loginUser(@RequestBody LoginRequest request) {
        // 사용자의 이메일과 비밀번호를 인증하고, 인증에 성공하면 access token을 생성하여 반환합니다.
        boolean isAuthenticated = authService.authenticate(request.getEmail(), request.getPassword());
        if (isAuthenticated) {
            // 인증이 성공하면 새로운 access token을 생성합니다.
            String newAccessToken = tokenService.createNewAccessToken(request.getEmail());
            // 생성된 access token을 클라이언트에게 반환합니다.
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CreateAccessTokenResponse(newAccessToken));
        } else {
            // 인증이 실패하면 Unauthorized 상태 코드를 반환합니다.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/users/check-duplicate-id")
    public ResponseEntity<User> noDuplicatedEmail(@RequestParam String email){
        User target = userService.findByEmail(email);
        return (target != null)?
                ResponseEntity.status(HttpStatus.OK).body(target):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }




}