package leets.domain.attendance.service;

import leets.domain.attendance.domain.Attendance;
import leets.domain.attendance.domain.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public List<Attendance> getReservations(Long userId) {
        return attendanceRepository.findAllByUser_Id(userId);
    }
}
