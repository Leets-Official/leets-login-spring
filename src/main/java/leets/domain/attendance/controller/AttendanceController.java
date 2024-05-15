package leets.domain.attendance.controller;

import leets.domain.attendance.domain.Attendance;
import leets.domain.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public ResponseEntity<List<Attendance>> getAttendances() {
        List<Attendance> reservations = attendanceService.getReservations(1L);
        
        return ResponseEntity.ok(reservations);
    }
}
