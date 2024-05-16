package leets.attendance.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {
    private String id;
    private String password;
    private String name;
    private String part;
}
