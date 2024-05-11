package leets.attendance.domain.attendance.presentation;


import leets.attendance.domain.attendance.application.AttendanceService;
import leets.attendance.global.dto.ResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static leets.attendance.domain.attendance.presentation.AttendanceMessageEnum.UPDATE_SUCCESS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PatchMapping
    public ResponseDto<String> updateAttendance(Authentication authentication) {
        return ResponseDto.of(OK.value(), attendanceService.updateAttendance(authentication));
    }

    @GetMapping
    public void getAttendances() {

    }

    @GetMapping(value = "/rates")
    public void getAttendanceRates() {

    }
}
