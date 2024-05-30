package leets.attendance.dto;

import leets.attendance.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequest {
    private Long week;

    @Override
    public String toString() {
        return "AttendanceRequest{" +
                "week=" + week +
                '}';
    }
}
