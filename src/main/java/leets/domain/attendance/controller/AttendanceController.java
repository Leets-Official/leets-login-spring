package leets.domain.attendance.controller;

import leets.domain.attendance.controller.dto.AttendanceResponse;
import leets.domain.attendance.service.AttendanceService;
import leets.global.config.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAttendances(AuthUser authUser) {
        List<AttendanceResponse> responses = attendanceService.getReservations(authUser.getId());

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<Void> attendant(AuthUser authUser) {
        AttendanceResponse response = attendanceService.attendant(authUser);

        URI uri = URI.create("/attendances/" + response.attendanceId());
        return ResponseEntity.created(uri).build();
    }
}
