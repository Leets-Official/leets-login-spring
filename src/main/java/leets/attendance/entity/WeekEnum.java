package leets.attendance.entity;

import java.time.LocalDate;

public enum WeekEnum {
    WEEK_1(LocalDate.of(2024,3,28)),
    WEEK_2(LocalDate.of(2024,4,4)),
    WEEK_3(LocalDate.of(2024,4,11)),
    WEEK_4(LocalDate.of(2024,4,18)),
    WEEK_5(LocalDate.of(2024,4,25)),//중간고사 출석체크 X
    WEEK_6(LocalDate.of(2024,5,2)),
    WEEK_7(LocalDate.of(2024,5,9)),
    WEEK_8(LocalDate.of(2024,5,16)),
    WEEK_9(LocalDate.of(2024,5,23)),
    WEEK_10(LocalDate.of(2024,5,30)),
    WEEK_11(LocalDate.of(2024,6,6));


    private final LocalDate date;
    WeekEnum(LocalDate date) {
        this.date = date;
    }
    public boolean isToday() {
        return date.isEqual(LocalDate.now());
    }

    public LocalDate getDate() {
        return date;
    }

}