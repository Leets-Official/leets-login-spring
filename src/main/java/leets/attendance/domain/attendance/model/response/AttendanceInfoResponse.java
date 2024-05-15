package leets.attendance.domain.attendance.model.response;

import java.time.LocalDate;
import leets.attendance.domain.member.entity.Member;
import leets.attendance.domain.member.entity.Part;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class AttendanceInfoResponse {
  private String name;
  private Part part;
  private LocalDate date;

  public static AttendanceInfoResponse of(Member member, LocalDate date) {
    return AttendanceInfoResponse.builder()
        .name(member.getName())
        .part(member.getPart())
        .date(date)
        .build();
  }
}
