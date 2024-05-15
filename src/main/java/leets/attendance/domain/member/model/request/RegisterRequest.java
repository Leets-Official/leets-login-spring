package leets.attendance.domain.member.model.request;

import leets.attendance.domain.member.entity.Part;
import lombok.Getter;

@Getter
public class RegisterRequest {

  private String name;
  private Part part;
  private String username;
  private String password;
  private String passwordConfirmation;
}
