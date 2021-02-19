package com.javamaster.b2c.cloud.test.learn.java.java8.newdatatime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * Created on 2019/1/9.<br/>
 *
 * @author yudong
 */
public class ZoneTest {
    public static void main(String[] args) {
        System.out.println(TimeZone.getDefault());
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        System.out.println(zoneId);
        System.out.println(ZoneId.of("GMT+8"));
        System.out.println(ZoneId.systemDefault());
        System.out.println(TimeZone.getDefault().toZoneId());

        ZoneId romeZone = ZoneId.systemDefault();
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        System.out.println(zdt1);
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        System.out.println(zdt2);
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        System.out.println(zdt3);

        LocalDate localDate = LocalDate.now();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("UTC"));
        System.out.println(zonedDateTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime1 = localDateTime.atZone(ZoneId.of("UTC"));
        System.out.println(zonedDateTime1);

        Instant instantFromDateTime = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        System.out.println(instantFromDateTime);

        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
        System.out.println(timeFromInstant);

        System.out.println(Instant.now());
        System.out.println(Instant.now().atZone(ZoneId.of("Asia/Shanghai")));
        System.out.println(Instant.now().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime());

    }

}
