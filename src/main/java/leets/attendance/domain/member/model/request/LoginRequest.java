package leets.attendance.domain.member.model.request;

import lombok.Getter;

@Getter
public class LoginRequest {

  private String username;
  private String password;
}
