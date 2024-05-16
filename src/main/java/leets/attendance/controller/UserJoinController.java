package leets.attendance.controller;

import leets.attendance.dto.UserDTO;
import leets.attendance.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserJoinController {

    private final UserJoinService userJoinService;

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(UserDTO dto){
        userJoinService.register(dto);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/users/login")).build();
    }

    @GetMapping("/users/check-duplicate-id")
    public ResponseEntity<String> checkDuplicateId(UserDTO dto){
        try {
            userJoinService.validateDuplicateUser(dto.getUserid());
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 ID 입니다.");
        }
        return ResponseEntity.ok("사용 가능한 ID 입니다.");
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
