package com.bba.appt;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Slf4j
public class DatePeriodUtil {

    public static DatePeriodDto calculateDates(String period, LocalDate date, boolean firstDayOfWeekSunday) {
        if ("d".equalsIgnoreCase(period)) {
            return DatePeriodDto.builder().period(period).start(date).end(date).build();
        } else if ("m".equalsIgnoreCase(period)) {
            return calculateMonthlyPeriod(date);
        } else if ("w".equalsIgnoreCase(period)) {
            return calculateWeeklyPeriod(date, firstDayOfWeekSunday);
        } else {
            throw new RuntimeException("Unrecogized period [d|m|w], " + period + " for date " + date);
        }
    }

    private static DatePeriodDto calculateMonthlyPeriod(LocalDate date) {
        log.debug("month for {}", date);
        LocalDate from = LocalDate.of(date.getYear(), date.getMonth(), 1);
        LocalDate to = LocalDate.of(date.getYear(), date.getMonth(), date.getMonth().maxLength());
        return DatePeriodDto.builder().period("m").start(from).end(to).build();
    }

    private static DatePeriodDto calculateWeeklyPeriod(LocalDate date, boolean firstDayOfWeekSunday) {
        log.debug("week for {} {}", date, firstDayOfWeekSunday);
        int offset;
        if (firstDayOfWeekSunday) {
            offset = (date.getDayOfWeek() == DayOfWeek.SUNDAY) ? -7 : 0;
        } else {
            offset = -1;
        }
        LocalDate from = date.minusDays(date.getDayOfWeek().getValue() + offset);
        LocalDate to = from.plusDays(6);
        return DatePeriodDto.builder().period("w").start(from).end(to).build();
    }
}
