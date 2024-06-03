package leets.attendance.controller;


import leets.attendance.config.jwt.TokenProvider;
import leets.attendance.domain.Attendance;
import leets.attendance.dto.AttendanceRequest;
import leets.attendance.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j //able to leave logs
@RestController
public class AttendanceApiController {
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    private TokenProvider tokenProvider;

    @PatchMapping("/attendances")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Attendance> attend(@RequestBody AttendanceRequest attendanceRequest, @RequestHeader("Authorization") String token) {

        String jwtToken = token.substring(7);
        System.out.println("token : " + jwtToken);
        Long userId = tokenProvider.getId(jwtToken);

        try {
            Attendance updatedAttendance = attendanceService.saveAttend(attendanceRequest.getWeekEnum(), userId);
            return ResponseEntity.ok(updatedAttendance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }


    }
}
