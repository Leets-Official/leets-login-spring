package leets.attendance.controller;

import leets.attendance.domain.Attendances;
import leets.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PatchMapping("/attendances")
    public ResponseEntity<String> updateAttendances() {
        String  userid = SecurityContextHolder.getContext().getAuthentication().getName();
        attendanceService.makeAttendance(userid, LocalDate.now());
        return ResponseEntity.ok("Attendance updated");
    }

    @GetMapping("/attendances")
    public ResponseEntity<List<Attendances>> getAllAttendances(){
        String  userid = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Attendances> allAttendanceRecord = attendanceService.getAllAttendanceRecord(userid);
        return ResponseEntity.ok(allAttendanceRecord);
    }

    @GetMapping("/attendances/rates")
    public ResponseEntity<Double> getAttendances() {
        String  userid = SecurityContextHolder.getContext().getAuthentication().getName();
        double attendanceRate = attendanceService.getAttendanceRate(userid, LocalDate.now());
        return ResponseEntity.ok(attendanceRate);
    }
}
