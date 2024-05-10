package leets.attendance.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "ID는 필수 입니다")
    private String userid;

    @NotBlank(message = "비밀번호는 필수 입니다")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입니다")
    private String confirmPassword;

    @NotBlank(message = "이름은 필수 입니다")
    private String name;

    @NotBlank(message = "파트명은 필수 입니다")
    private String partName;
}
