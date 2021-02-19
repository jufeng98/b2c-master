package com.javamaster.b2c.cloud.test.learn.java.java8.newdatatime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Created on 2019/1/9.<br/>
 *
 * @author yudong
 */
public class FormatTest {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s1);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s2);

        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date1);
        System.out.println(date2);

        System.out.println(formatter.format(LocalDate.now()));
        System.out.println(LocalDate.parse("01/01/2018", formatter));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd", Locale.SIMPLIFIED_CHINESE);
        String formattedDate = date.format(dateTimeFormatter);
        System.out.println(formattedDate);
        date2 = LocalDate.parse(formattedDate, dateTimeFormatter);
        System.out.println(date2);

        System.out.println(formatter1.format(LocalDateTime.now()));
        System.out.println(LocalDateTime.now().format(formatter1));

        DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        System.out.println(date.format(italianFormatter));
    }
}
