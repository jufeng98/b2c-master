package com.javamaster.b2c.cloud.test.learn.java.resolveconfuse;

import java.util.Random;

/**
 * Created on 2018/9/11.<br/>
 *
 * @author yudong
 */
public class Confusing {
    public static void main(String[] args) {

    }

    public static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    public static void change() {
        System.out.println(2.00 - 1.10);
    }

    public static void longDivision() {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }

    public static void elementary() {
        System.out.println(12345 + 5432l);
    }

    public static void multicast() {
        //如果最初的数值类型是有符号的，那么
        //就执行符号扩展；如果它是 char，那么不管它将要被转换成什么类型，都执行
        //零扩展。
        System.out.println((int) (char) (byte) -1);
    }

    public static void dosEquis() {
        //•  如果第二个和第三个操作数具有相同的类型，那么它就是条件表达式的类
        //型。换句话说，你可以通过绕过混合类型的计算来避免大麻烦。
        //•  如果一个操作数的类型是 T，T 表示 byte、short 或 char，而另一个操作
        //数是一个 int 类型的常量表达式，它的值是可以用类型 T 表示的，那么条
        //件表达式的类型就是 T。
        //•  否则，将对操作数类型运用二进制数字提升，而条件表达式的类型就是第
        //二个和第三个操作数被提升之后的类型。
        char x = 'X';
        int i = 0;
        System.out.println(true ? x : 0);
        System.out.println(false ? i : x);
    }

    public static void lastLaugh() {
        System.out.print("H" + "a");
        System.out.print('H' + 'a');
    }

    public static void abc() {
        String letters = "ABC";
        char[] numbers = {'1', '2', '3'};
        System.out.println(letters + "easyas " + numbers);
    }

    public static void animalFarm() {
        final String pig = "length:10";
        final String dog = "length: " + pig.length();
        System.out.println("Animals are equal: " + pig == dog);
    }

    public static void me() {
        System.out.println(Confusing.class.getName().replaceAll(".", "/") + ".class");
    }

    public static void Rhymes() {
        StringBuffer word = null;
        Random rnd = new Random();
        switch (rnd.nextInt(2)) {
            case 1:
                word = new StringBuffer('P');
            case 2:
                word = new StringBuffer('G');
            default:
                word = new StringBuffer('M');
        }
        word.append('a');
        word.append('i');
        word.append('n');
        System.out.println(word);
    }

    public static void increment() {
        int j = 0;
        for (int i = 0; i < 100; i++)
            j = j++;
        System.out.println(j);
    }

    public static void inTheLoop() {
        final int END = Integer.MAX_VALUE;
        final int START = END - 100;
        int count = 0;
        for (int i = START; i <= END; i++)
            count++;
        System.out.println(count);
    }

    public static void loop() {
//        for (int i = start; i <= start + 1; i++) {}
//        While (i == i + 1) {}
//        while (i != i) {}
//        while (i != i + 0) {}
//        while (i <= j&& j <= i && i != j) {}
//        while (i != 0 && i == -i) {}
    }

    public static void count() {
        final int START = 2000000000;
        int count = 0;
        for (float f = START; f < START + 50; f++)
            count++;
        System.out.println(count);
    }

    public static void clock() {
        int minutes = 0;
        for (int ms = 0; ms < 60 * 60 * 1000; ms++)
            if (ms % 60 * 1000 == 0)
                minutes++;
        System.out.println(minutes);
    }

    public static boolean indecisive() {
        try {
            return true;
        } finally {
            return false;
        }
    }

    public static void helloGoodBye() {
        try {
            System.out.println("Hello world");
            System.exit(0);
        } finally {
            System.out.println("Goodbye world");
        }
    }
}
