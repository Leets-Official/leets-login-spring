package leets.attendance.dto;

import leets.attendance.domain.User;
import leets.attendance.domain.WeekEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequest {
    private WeekEnum weekEnum;

    @Override
    public String toString() {
        return "AttendanceRequest{" +
                "week=" + weekEnum +
                '}';
    }
}
