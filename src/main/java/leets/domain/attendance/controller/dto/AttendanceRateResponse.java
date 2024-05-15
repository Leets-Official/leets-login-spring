package leets.domain.attendance.controller.dto;

public record AttendanceRateResponse(
        Float attendantRate,
        Float absentRate,
        Float lateRate
) {
}
