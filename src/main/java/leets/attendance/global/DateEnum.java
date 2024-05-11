package leets.attendance.global;


import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;


public enum DateEnum {
    WEEK_1(LocalDate.of(2024, 4, 4)),
    WEEK_2(LocalDate.of(2024, 4, 11)),
    WEEK_3(LocalDate.of(2024, 4, 18)),
    WEEK_4(LocalDate.of(2024, 5, 2)),
    WEEK_5(LocalDate.of(2024, 5, 9)),
    WEEK_6(LocalDate.of(2024, 5, 16)),
    WEEK_7(LocalDate.of(2024, 5, 23)),
    WEEK_8(LocalDate.of(2024, 5, 30));

    private final LocalDate startDate;

    DateEnum(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public static DateEnum getByWeekNumber(int weekNumber) {
        if (weekNumber < 1 || weekNumber > values().length) {
            throw new IllegalArgumentException("Invalid week number: " + weekNumber);
        }
        return values()[weekNumber - 1];
    }
}
