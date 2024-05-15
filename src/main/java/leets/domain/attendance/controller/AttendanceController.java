package leets.domain.attendance.controller;

import leets.domain.attendance.controller.dto.AttendanceRateResponse;
import leets.domain.attendance.controller.dto.AttendanceResponse;
import leets.domain.attendance.controller.dto.AttendanceSaveRequest;
import leets.domain.attendance.service.AttendanceService;
import leets.global.config.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Void> attendant(AuthUser authUser,
                                          @RequestBody AttendanceSaveRequest request) {
        AttendanceResponse response = attendanceService.attendant(authUser, request);

        URI uri = URI.create("/attendances/" + response.attendanceId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAttendances(AuthUser authUser) {
        List<AttendanceResponse> responses = attendanceService.getReservations(authUser);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/rates")
    public ResponseEntity<AttendanceRateResponse> getAttendanceRate(AuthUser authUser) {
        AttendanceRateResponse responses = attendanceService.getAttendanceRate(authUser);

        return ResponseEntity.ok(responses);
    }
}
