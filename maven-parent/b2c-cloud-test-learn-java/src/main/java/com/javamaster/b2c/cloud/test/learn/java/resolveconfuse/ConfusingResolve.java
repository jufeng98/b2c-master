package com.javamaster.b2c.cloud.test.learn.java.resolveconfuse;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created on 2018/9/11.<br/>
 *
 * @author yudong
 */
public class ConfusingResolve {
    public static void main(String[] args) {

    }

    public static boolean isOdd(int i) {
        //(i & 1) != 0;
        return i % 2 != 0;
    }

    public static void change() {
        //拙劣的解决方案——仍旧是使用二进制浮点数
        System.out.printf("%.2f%n", 2.00 - 1.10);
        System.out.println((200 - 110) + "cents");
        System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));
    }

    public static void longDivision() {
        final long MICROS_PER_DAY = 24L * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24L * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }

    public static void elementary() {
        System.out.println(12345 + 5432L);
    }

    public static void lastLaugh() {
        System.out.print("H" + "a");
        System.out.print("" + 'H' + 'a');
    }

    public static void abc() {
        String letters = "ABC";
        char[] numbers = {'1', '2', '3'};
        System.out.println(letters + "easyas ");
        System.out.println(numbers);
    }

    public static void animalFarm() {
        final String pig = "length:10";
        final String dog = "length: " + pig.length();
        System.out.println("Animals are equal: " + pig.equals(dog));
    }

    public static void me() {
        System.out.println(Confusing.class.getName().replaceAll("\\.", "/") + ".class");
        System.out.println(Confusing.class.getName().replace(".", "/") + ".class");
    }

    public static void Rhymes() {
        StringBuffer word = null;
        Random rnd = new Random();
        switch (rnd.nextInt(3)) {
            case 1:
                word = new StringBuffer("P");
                break;
            case 2:
                word = new StringBuffer("G");
                break;
            default:
                word = new StringBuffer("M");
        }
        word.append('a');
        word.append('i');
        word.append('n');
        System.out.println(word);
    }

    public static void increment() {
        int j = 0;
        for (int i = 0; i < 100; i++)
            j++;
        System.out.println(j);
    }

    public static void inTheLoop() {
        final int END = Integer.MAX_VALUE;
        final int START = END - 100;
        int count = 0;
        for (long i = START; i <= END; i++)
            count++;
        System.out.println(count);
    }

    public static void loop() {
//        int start=Integer.MAX_VALUE;
//        for (int i = start; i <= start + 1; i++) {}
//        double i=Double.POSITIVE_INFINITY;
//        While (i == i + 1) {}
//        double i=Double.NaN;
//        while (i != i) {}
//        String i="s";
//        while (i != i + 0) {}
//        Integer i = new Integer(0);
//        Integer j = new Integer(0);
//        while (i <= j&& j <= i && i != j) {}
//        int i=Integer.MIN_VALUE;
//        while (i != 0 && i == -i) {}
    }

    public static void count() {
        final int START = 2000000000;
        int count = 0;
        for (int f = START; f < START + 50; f++)
            count++;
        System.out.println(count);
    }

    public static void clock() {
        int minutes = 0;
        final int MS_PER_HOUR = 60 * 60 * 1000;
        final int MS_PER_MINUTE = 60 * 1000;
        for (int ms = 0; ms < MS_PER_HOUR; ms++) {
            if (ms % MS_PER_MINUTE == 0)
                minutes++;
        }
        System.out.println(minutes);
    }

    public static void helloGoodBye() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Hello world");
            }
        });
        System.exit(0);
    }

}
