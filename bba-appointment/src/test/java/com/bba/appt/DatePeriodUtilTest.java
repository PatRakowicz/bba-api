package com.bba.appt;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatePeriodUtilTest {

    @Test
    public void calculateDayDates() {
        LocalDate date = LocalDate.now();
        DatePeriodDto result = DatePeriodUtil.calculateDates("d", date, false);
        assertNotNull(result);
        assertEquals(date, result.getStart());
        assertEquals(date, result.getEnd());
        assertEquals("d", result.getPeriod());
    }

    @Test
    public void calculateMonthlyDates() {
        LocalDate date = LocalDate.parse("2019-12-22");
        DatePeriodDto result = DatePeriodUtil.calculateDates("m", date, false);
        assertNotNull(result);
        assertEquals(LocalDate.parse("2019-12-01"), result.getStart());
        assertEquals(LocalDate.parse("2019-12-31"), result.getEnd());
        assertEquals("m", result.getPeriod());
    }

    @Test
    public void calculateWeeklyDatesMidWeekStartsOnMonday() {
        LocalDate date = LocalDate.parse("2019-12-19");
        DatePeriodDto result = DatePeriodUtil.calculateDates("w", date, false);
        assertNotNull(result);
        assertEquals(LocalDate.parse("2019-12-16"), result.getStart());
        assertEquals(LocalDate.parse("2019-12-22"), result.getEnd());
        assertEquals("w", result.getPeriod());
    }

    @Test
    public void calculateWeeklyDatesMidWeekStartsOnSunday() {
        LocalDate date = LocalDate.parse("2019-12-19");
        DatePeriodDto result = DatePeriodUtil.calculateDates("w", date, true);
        assertEquals(LocalDate.parse("2019-12-15"), result.getStart());
        assertEquals(LocalDate.parse("2019-12-21"), result.getEnd());
    }

    @Test
    public void calculateWeeklyDatesEndWeekStartsOnMonday() {
        LocalDate date = LocalDate.parse("2019-12-22");
        DatePeriodDto result = DatePeriodUtil.calculateDates("w", date, false);
        assertEquals(LocalDate.parse("2019-12-16"), result.getStart());
        assertEquals(LocalDate.parse("2019-12-22"), result.getEnd());
    }

    @Test
    public void calculateWeeklyDatesEndWeekStartsOnSunday() {
        LocalDate date = LocalDate.parse("2019-12-22");
        DatePeriodDto result = DatePeriodUtil.calculateDates("w", date, true);
        assertEquals(LocalDate.parse("2019-12-22"), result.getStart());
        assertEquals(LocalDate.parse("2019-12-28"), result.getEnd());
    }

    @Test
    public void calculateWeeklyDatesStartWeekStartsOnMonday() {
        LocalDate date = LocalDate.parse("2019-12-23");
        DatePeriodDto result = DatePeriodUtil.calculateDates("w", date, false);
        assertEquals(LocalDate.parse("2019-12-23"), result.getStart());
        assertEquals(LocalDate.parse("2019-12-29"), result.getEnd());
    }

    @Test
    public void calculateWeeklyDatesStartWeekStartsOnSunday() {
        LocalDate date = LocalDate.parse("2019-12-23");
        DatePeriodDto result = DatePeriodUtil.calculateDates("w", date, true);
        assertEquals(LocalDate.parse("2019-12-22"), result.getStart());
        assertEquals(LocalDate.parse("2019-12-28"), result.getEnd());
    }

    @Test
    public void calculateWeeklyStartsOnMonday() {
        ImmutableList.of(23, 24, 25, 26, 27, 28, 29).forEach(day -> {
            LocalDate date = LocalDate.parse("2019-12-" + day);
            DatePeriodDto result = DatePeriodUtil.calculateDates("w", date, false);
            assertEquals(LocalDate.parse("2019-12-23"), result.getStart());
            assertEquals(LocalDate.parse("2019-12-29"), result.getEnd());
        });
    }

    @Test
    public void calculateWeeklyStartsOnSunday() {
        ImmutableList.of(22, 23, 24, 25, 26, 27, 28).forEach(day -> {
            LocalDate date = LocalDate.parse("2019-12-" + day);
            DatePeriodDto result = DatePeriodUtil.calculateDates("w", date, true);
            assertEquals(LocalDate.parse("2019-12-22"), result.getStart());
            assertEquals(LocalDate.parse("2019-12-28"), result.getEnd());
        });
    }
}
