package leets.domain.attendance.controller.dto;

import leets.domain.attendance.domain.AttendanceStatus;

import java.time.LocalDateTime;

public record AttendanceResponse(
        Long attendanceId,
        AttendanceStatus status,
        LocalDateTime localDateTime
) {
}
