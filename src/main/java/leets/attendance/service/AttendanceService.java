package leets.attendance.service;

import leets.attendance.domain.Attendance;
import leets.attendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AttendanceService {
    @Autowired
    private final AttendanceRepository attendanceRepository;

    public Attendance saveAttend(Long week, Long userId) {
        Attendance target = attendanceRepository.findAttendanceById(userId);
        switch (week.intValue()) {
            case 1:
                target.setFirstWeek(true);
                break;
            case 2:
                target.setSecondWeek(true);
                break;
            case 3:
                target.setThirdWeek(true);
                break;
            case 4:
                target.setFourthWeek(true);
                break;
            case 5:
                target.setFifthWeek(true);
                break;
            case 6:
                target.setSixthWeek(true);
                break;
            case 7:
                target.setSeventhWeek(true);
                break;
            case 8:
                target.setEighthWeek(true);
                break;
            default:
                throw new IllegalArgumentException("Invalid week: " + week);
        }
        return attendanceRepository.save(target);

    }


    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }


}
