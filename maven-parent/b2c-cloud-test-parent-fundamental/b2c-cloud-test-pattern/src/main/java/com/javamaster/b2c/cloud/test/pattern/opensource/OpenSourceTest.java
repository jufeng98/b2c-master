package com.javamaster.b2c.cloud.test.pattern.opensource;

import ch.lambdaj.Lambda;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import gnu.trove.decorator.TIntListDecorator;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created on 2018/7/6.
 *
 * @author yudong
 */
public class OpenSourceTest {
    public static void main(String[] args) {

        ImmutableList<String> immutableList = ImmutableList.of("hello", "world");
        System.out.println(immutableList);

        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("A", "12345");
        multimap.put("A", "23135");
        System.out.println(multimap.get("A"));

        System.out.println(RandomUtils.nextInt(1, 100));
        System.out.println(RandomStringUtils.randomAlphabetic(12));
        System.out.println(DateUtils.addMonths(new Date(), 2));
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));

        DateTime dateTime = new DateTime();
        System.out.println(dateTime.dayOfWeek().getAsString());
        System.out.println(dateTime.toLocalDate());
        System.out.println(dateTime.toString(DateTimeFormat.forPattern("yyyy年MM月dd日")));
        System.out.println(dateTime.plusDays(3).toLocalDate());
        System.out.println(Days.daysBetween(dateTime, new DateTime("2019-12-12")).getDays());
        MutableDateTime mutableDateTime = new MutableDateTime();
        DateTime dateTime1 = dateTime.plusYears(10);
        while (mutableDateTime.isBefore(dateTime1)) {
            mutableDateTime.addDays(1);
            if (mutableDateTime.getDayOfMonth() == 13 && mutableDateTime.getDayOfWeek() == 5) {
                System.out.println("black Friday:" + mutableDateTime);
            }
        }
        DateTime time = new DateTime();
        System.out.println(time.withZone(DateTimeZone.UTC));
        Date date = time.toDate();
        time = new DateTime(date);
        System.out.println(time);

        TIntList list = new TIntArrayList(5) {{
            add(2);
            add(5);
            add(6);
        }};
        list.transformValues((int i) -> i * 2);
        System.out.println(list);
        System.out.println(list.grep((int i) -> i > 10));

        List<Integer> list1 = new TIntListDecorator(list);
        System.out.println(list1);

        System.out.println(Lambda.sort(list1, Lambda.on(Integer.class), Collections.reverseOrder()));
        System.out.println(Lambda.max(list1, Lambda.on(Integer.class)));


    }
}
