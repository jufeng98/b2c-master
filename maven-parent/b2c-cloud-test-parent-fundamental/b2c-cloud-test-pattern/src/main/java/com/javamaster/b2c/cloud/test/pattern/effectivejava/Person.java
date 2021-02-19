package com.javamaster.b2c.cloud.test.pattern.effectivejava;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created on 2018/8/3.</br>
 *
 * @author yudong
 */
public class Person {

    public static void main(String[] args) {
        System.out.println(new Person(new Date()).isBabyBoomer());
    }

    private final Date birthDate;

    private static final Date START;
    private static final Date END;

    static {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        START = calendar.getTime();
        calendar.set(1964, Calendar.JANUARY, 1, 0, 0, 0);
        END = calendar.getTime();
    }

    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBabyBoomer() {
        return birthDate.after(START) && birthDate.before(END);
    }
}
