package com.javamaster.b2c.cloud.test.learn.java.java8.newdatatime;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        // first version
        // if (temporal.get(ChronoField.DAY_OF_WEEK) >= 1 && temporal.get(ChronoField.DAY_OF_WEEK) <= 4) {
        //     return temporal.plus(1, ChronoUnit.DAYS);
        // } else {
        //     return temporal.with(nextOrSame(DayOfWeek.MONDAY));
        // }

        DayOfWeek dow =  DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dow == DayOfWeek.FRIDAY) {
            dayToAdd = 3;
        } else if (dow == DayOfWeek.SATURDAY) {
            dayToAdd = 2;
        }

        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
