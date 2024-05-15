package leets.domain.attendance.domain;

import java.util.List;
import java.util.function.Predicate;

public class Attendances {

    private final List<Attendance> attendances;

    public Attendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public float attendantRate() {
        return getCount(Attendance::isAttendant);
    }

    public float absentRate() {
        return getCount(Attendance::isAbsent);
    }

    public float lateRate() {
        return getCount(Attendance::isLate);
    }

    private float getCount(Predicate<Attendance> condition) {
        long count = attendances.stream()
                .filter(condition)
                .count();

        return ((float) count / attendances.size()) * 100;
    }
}
