package domain.attendance;

import domain.attendance.RatesResponse;
import domain.attendance.Response;
import domain.attendance.controller.dto.AttendanceSaveRequest;
import domain.Attendance;
import domain.Attendances;
import domain.repository.AttendanceRepository;
import domain.user.domain.User;
import domain.user.domain.repository.UserRepository;
import global.config.AuthUser;
import global.exception.LeetsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class Service {private final UserRepo userRepo;
    private final AttendanceRepository attendanceRepository;

    public List<Response> getReservations(AuthUser authUser) {
        List<Attendance> attendances = attendanceRepository.findAllByUser_Id(authUser.getId());

        return attendances.stream()
                .map(attendance -> new Response(attendance.getId(), attendance.getStatus(), attendance.getLocalDateTime()))
                .toList();
    }

    public AttendanceResponse attendant(AuthUser authUser, AttendanceSaveRequest request) {
        User user = userRepository.findById(authUser.getId())
                .orElseThrow(() -> new LeetsException(NOT_FOUND, "사용자를 찾을 수 없습니다"));

        Attendance attendance = new Attendance(user, request.status(), LocalDateTime.now());
        Attendance save = attendanceRepository.save(attendance);

        return new AttendanceResponse(save.getId(), save.getStatus(), save.getLocalDateTime());
    }

    public AttendanceRateResponse getAttendanceRate(AuthUser authUser) {
        User user = userRepository.findById(authUser.getId())
                .orElseThrow(() -> new LeetsException(NOT_FOUND, "사용자를 찾을 수 없습니다"));

        Attendances attendances = new Attendances(attendanceRepository.findAllByUser_Id(user.getId()));
        return new AttendanceRateResponse(attendances.attendantRate(), attendances.absentRate(), attendances.lateRate());
    }
}
