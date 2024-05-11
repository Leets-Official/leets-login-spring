package leets.attendance.domain.user.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserResponseMessage {
    SUCCESS_LOGIN("로그인에 성공했습니다."),
    SUCCESS_REGISTER("회원가입에 성공했습니다.");
    private String message;
}
