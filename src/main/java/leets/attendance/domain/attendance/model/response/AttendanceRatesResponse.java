package leets.attendance.domain.attendance.model.response;

import java.util.List;
import leets.attendance.domain.attendance.entity.AttendanceStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class AttendanceRatesResponse {
  private AttendanceInfoResponse attendanceInfoResponse;
  private List<AttendanceStatus> attendanceStatuses;
  private double attendanceRate;

  public static AttendanceRatesResponse of(AttendanceInfoResponse response, List<AttendanceStatus> attendanceStatuses,
      double rate) {
    return AttendanceRatesResponse.builder()
        .attendanceInfoResponse(response)
        .attendanceStatuses(attendanceStatuses)
        .attendanceRate(rate)
        .build();
  }
}
