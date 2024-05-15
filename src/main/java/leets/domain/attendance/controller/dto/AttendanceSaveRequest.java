package leets.domain.attendance.controller.dto;

import leets.domain.attendance.domain.AttendanceStatus;

public record AttendanceSaveRequest(
        AttendanceStatus status
) {
}
