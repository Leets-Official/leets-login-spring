package leets.attendance.service;

import leets.attendance.domain.Attendance;
import leets.attendance.domain.User;
import leets.attendance.domain.WeekEnum;
import leets.attendance.repository.AttendanceRepository;
import leets.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AttendanceService {
    @Autowired
    private final AttendanceRepository attendanceRepository;
    @Autowired
    private final UserRepository userRepository;

    public Attendance saveAttend(WeekEnum weekEnum, Long userId) {


        Optional<User> target = userRepository.findById(userId);


        Attendance attendance =
        return attendanceRepository.save();

    }


    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }


}
