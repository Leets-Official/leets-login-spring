package leets.attendance.src.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDto {

    private String username;
    private String pwd;
    private String checkPwd;
    private String name;
    private String part;
    private Boolean isChecked;  // 아이디 중복 검사 여부
}
