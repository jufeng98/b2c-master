package com.javamaster.b2c.cloud.test.learn.java.java8.newdatatime;

import org.junit.Test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class DateTimeTest {

    @Test
    public void test() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        System.out.println("year:" + year);
        Month month = date.getMonth();
        System.out.println("month:" + month);
        int day = date.getDayOfMonth();
        System.out.println("day:" + day);
        DayOfWeek dow = date.getDayOfWeek();
        System.out.println("day of week:" + dow);
        int len = date.lengthOfMonth();
        System.out.println("len:" + len);
        boolean leap = date.isLeapYear();
        System.out.println("leap:" + leap);

        LocalDate today = LocalDate.now();
        System.out.println("today:" + today);
        System.out.println(today.get(ChronoField.YEAR));
        System.out.println(today.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(today.get(ChronoField.DAY_OF_MONTH));

        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011).with(ChronoField.MONTH_OF_YEAR, 9).withDayOfMonth(25);
        System.out.println(date2);

        LocalDate date3 = date1.plusWeeks(1).minusYears(3).plus(6, ChronoUnit.MONTHS);
        System.out.println(date3);
    }

    @Test
    public void test1() {
        LocalTime time = LocalTime.of(13, 45, 20);
        System.out.println(time);

        LocalTime nowTime = LocalTime.now();
        System.out.println(nowTime.getHour());
        System.out.println(nowTime.getMinute());
        System.out.println(nowTime.getSecond());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.toLocalDate());
        System.out.println(localDateTime.toLocalTime());

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println(Clock.systemUTC().millis());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test2() {
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));

        Duration d1 = Duration.between(LocalTime.parse("13:13:13"), LocalTime.now());
        System.out.println(d1.getSeconds());

        Duration d2 = Duration.between(LocalDateTime.parse("2017-01-03T13:13:13"), LocalDateTime.now());
        System.out.println(d2.getSeconds());

        Duration d3 = Duration.between(Instant.parse("2017-01-03T05:13:13Z"), Instant.now());
        System.out.println(d3.getSeconds());

        System.out.println(Duration.ofMinutes(3).getSeconds());
        System.out.println(Duration.of(3, ChronoUnit.MINUTES).getSeconds());

        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));
        System.out.println(tenDays.getDays());

        System.out.println(Period.of(2008, 8, 8).getDays());
        System.out.println(Period.ofDays(10).getDays());
        System.out.println(Period.ofWeeks(3).getDays());

        // 只能计算同月的天数、同年的月数，不能计算隔月的天数以及隔年的月数
        System.out.println(Period.between(LocalDate.of(2008, 8, 8), LocalDate.now()).getYears());
        System.out.println(Period.between(LocalDate.of(2008, 8, 8), LocalDate.now()).getMonths());
        System.out.println(Period.between(LocalDate.of(2008, 8, 8), LocalDate.now()).getDays());

        ZonedDateTime zonedDateTime = ZonedDateTime.of(2008, 8, 8, 0, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime nowZonedDateTime = ZonedDateTime.now();
        Instant instant = Instant.from(zonedDateTime);
        Instant nowInstant = Instant.from(nowZonedDateTime);
        Duration duration = Duration.between(instant, nowInstant);
        System.out.println(duration.getSeconds() / 86400);

    }

    @Test
    public void test3() {
        org.joda.time.LocalDate localDate = new org.joda.time.LocalDate(2008, 8, 8);
        org.joda.time.LocalDate nowLocalDate = org.joda.time.LocalDate.now();
        org.joda.time.Period period = org.joda.time.Period.fieldDifference(localDate, nowLocalDate);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
}
