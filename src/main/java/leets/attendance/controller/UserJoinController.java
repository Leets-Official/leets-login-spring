package leets.attendance.controller;

import jakarta.validation.Valid;
import leets.attendance.dto.UserDTO;
import leets.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserJoinController {

    private final UserService userService;

    @PostMapping("/users/register")
    public String registerUser(@Valid UserDTO dto, BindingResult result){
        if(result.hasErrors()){
            return "/users/register";
        }
        userService.register(dto);
        return "redirect:/"; //개발을 위해 회원가입 성공 시 메인 페이지로
    }
}
