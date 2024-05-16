package leets.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String userid;

    private String password;

    private String confirmPassword;

    private String name;

    private String partName;
}
