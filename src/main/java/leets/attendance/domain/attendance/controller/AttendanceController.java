package leets.attendance.domain.attendance.controller;

import java.security.Principal;
import leets.attendance.domain.attendance.model.response.AttendanceInfoResponse;
import leets.attendance.domain.attendance.model.response.AttendanceRatesResponse;
import leets.attendance.domain.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class AttendanceController {

  private final AttendanceService attendanceService;

  @GetMapping
  public ResponseEntity<AttendanceInfoResponse> getAttendanceInfo(Principal connectedUser) {
    return ResponseEntity.ok(attendanceService.getAttendanceInfo(connectedUser));
  }

  @GetMapping("/rates")
  public ResponseEntity<AttendanceRatesResponse> getAttendanceRates(Principal connectedUser) {
    return ResponseEntity.ok(attendanceService.getAttendanceRates(connectedUser));
  }

  @PatchMapping
  public ResponseEntity<Void> attend(Principal connectedUser) {
    attendanceService.attend(connectedUser);
    return ResponseEntity.ok().build();
  }
}
