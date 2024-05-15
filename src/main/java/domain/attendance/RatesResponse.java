package domain.attendance;

import java.util.List;
import domain.attendance.AttendanceStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class RatesResponse {
    private Response Response;
    private List<AttendanceStatus> attendanceStatuses;
    private double attendanceRate;
    public static RatesResponse of(Response response, List<AttendanceStatus> attendanceStatuses,
                                             double rate) {
        return RatesResponse.builder()
                .Response(response)
                .attendanceStatuses(attendanceStatuses)
                .attendanceRate(rate)
                .build();
    }
}
