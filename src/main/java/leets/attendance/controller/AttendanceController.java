package leets.attendance.controller;

import leets.attendance.domain.Attendances;
import leets.attendance.service.AttendanceService;
import leets.attendance.service.UserDetailsServiceImpl;
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
    private final UserDetailsServiceImpl userDetailsService;

    @PatchMapping("/attendances")
    public ResponseEntity<String> updateAttendances() {

        String  userid = SecurityContextHolder.getContext().getAuthentication().getName();
        attendanceService.makeAttendance(userid, LocalDate.of(2024,3,29));
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
        double attendance = attendanceService.getAttendanceRate(userid, LocalDate.of(2024, 5, 29));
        return ResponseEntity.ok(attendance);
    }


}
