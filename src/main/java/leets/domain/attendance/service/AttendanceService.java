package leets.domain.attendance.service;

import leets.domain.attendance.controller.dto.AttendanceResponse;
import leets.domain.attendance.controller.dto.AttendanceSaveRequest;
import leets.domain.attendance.domain.Attendance;
import leets.domain.attendance.domain.repository.AttendanceRepository;
import leets.domain.user.domain.User;
import leets.domain.user.domain.repository.UserRepository;
import leets.global.config.AuthUser;
import leets.global.exception.LeetsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    public List<AttendanceResponse> getReservations(Long userId) {
        List<Attendance> attendances = attendanceRepository.findAllByUser_Id(userId);

        return attendances.stream()
                .map(attendance -> new AttendanceResponse(attendance.getId(), attendance.getStatus(), attendance.getLocalDateTime()))
                .toList();
    }

    public AttendanceResponse attendant(AuthUser authUser, AttendanceSaveRequest request) {
        User user = userRepository.findById(authUser.getId())
                .orElseThrow(() -> new LeetsException(NOT_FOUND, "사용자를 찾을 수 없습니다"));

        Attendance attendance = new Attendance(user, request.status(), LocalDateTime.now());
        Attendance save = attendanceRepository.save(attendance);

        return new AttendanceResponse(save.getId(), save.getStatus(), save.getLocalDateTime());
    }
}
