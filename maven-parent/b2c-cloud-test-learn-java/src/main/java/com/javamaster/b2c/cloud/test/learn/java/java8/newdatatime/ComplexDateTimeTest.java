package com.javamaster.b2c.cloud.test.learn.java.java8.newdatatime;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class ComplexDateTimeTest {

    @Test
    public void test() {
        LocalDate date1 = LocalDate.of(2019, 1, 11);
        System.out.println(date1);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date2);
        LocalDate date3 = date2.with(lastDayOfMonth());
        System.out.println(date3);
        System.out.println(date1.with(firstDayOfYear()));
        System.out.println(date1.with(new NextWorkingDay()));
    }

    @Test
    public void test1() {
        LocalDate date1 = LocalDate.now();
        System.out.println(date1);

        LocalDate nextWorkDay = date1.with(temporal -> {
            DayOfWeek dow =
                    DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(nextWorkDay);

        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow =
                            DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow == DayOfWeek.FRIDAY) {
                        dayToAdd = 3;
                    }
                    if (dow == DayOfWeek.SATURDAY) {
                        dayToAdd = 2;
                    }
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });
        nextWorkDay = date1.with(nextWorkingDay);
        System.out.println(nextWorkDay);
    }
}
