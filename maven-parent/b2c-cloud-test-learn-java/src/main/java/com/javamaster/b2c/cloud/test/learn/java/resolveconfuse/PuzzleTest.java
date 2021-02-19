package com.javamaster.b2c.cloud.test.learn.java.resolveconfuse;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author yudong
 * @date 2018/4/19
 */
public class PuzzleTest {
    public static void main(String[] args) throws Exception {
        stringCheese();
    }

    public static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    public static void change() {
        System.out.println(2.00 - 1.10);
    }

    public static void longDevide() {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }

    public static void interest() {
        System.out.println(12345 + 5432l);
//        从较窄的整型转换成较宽的整型时的符号扩展行为：如果最初的数值类型是有符号的，那么
//        就执行符号扩展；如果它是 char，那么不管它将要被转换成什么类型，都执行零扩展。
        System.out.println((int) (char) (byte) -1);
        byte x = 126, y = 2;
        System.out.println(x += y);
//        x = x + y;
        System.out.println();
    }

    public static void dosEquals() {
//        •  如果第二个和第三个操作数具有相同的类型，那么它就是条件表达式的类
//        型。换句话说，你可以通过绕过混合类型的计算来避免大麻烦。
//        •  如果一个操作数的类型是 T，T 表示 byte、short 或 char，而另一个操作
//        数是一个 int 类型的常量表达式，它的值是可以用类型 T 表示的，那么条
//        件表达式的类型就是 T。
//        •  否则，将对操作数类型运用二进制数字提升，而条件表达式的类型就是第
//        二个和第三个操作数被提升之后的类型。
        char x = 'X';
        int i = 0;
        System.out.println(true ? x : 0);
        System.out.println(false ? i : x);
    }

    public static void laughing() {
        System.out.print("H" + "a");
        System.out.print('H' + 'a');
        System.out.print("2 + 2 = " + 2 + 2);

        String letters = "ABC";
        char[] numbers = {'1', '2', '3'};
        System.out.println(letters + "easyas " + numbers);

        final String pig = "length:10";
        final String dog = "length: " + pig.length();
        System.out.println("Animals are equal: " + pig == dog);
    }

    public static void stringCheese() throws Exception {
        byte bytes[] = new byte[256];
        for (int i = 0; i < 256; i++) {
            bytes[i] = (byte) i;
        }
        String str = new String(bytes);
        for (int i = 0, n = str.length(); i < n; i++) {
            System.out.println((int) str.charAt(i) + " " + str.charAt(i));
        }
    }

    public static void random() {
        Random rnd = new Random();
        StringBuffer word = null;
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

    public static void bigDelight() {
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            if (b == 0x90)
                System.out.print("Joy!");
        }
    }

    public static void increment() {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        System.out.println(j);
    }

    public static void inTheLoop() {
        int END = Integer.MAX_VALUE;
        int START = END - 100;
        int count = 0;
        for (int i = START; i <= END; i++)
            count++;
        System.out.println(count);
    }
    //for (int i = start; i <= start + 1; i++) {}
    //While (i == i + 1) {}
    //while (i != i) {}
    //while (i != i + 0) {}
    //while (i <= j&& j <= i && i != j) {}
    //while (i != 0 && i == -i) {}

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

    public static boolean indesitive() {
        try {
            return true;
        } finally {
            return false;
        }
    }

    public static void loop() {
        int[][] tests = {{6, 5, 4, 3, 2, 1}, {1, 2},
                {1, 2, 3}, {1, 2, 3, 4}, {1}};
        int successCount = 0;
        try {
            int i = 0;
            while (true) {
                if (thirdElementIsThree(tests[i++]))
                    successCount++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        System.out.println(successCount);
    }

    private static boolean thirdElementIsThree(int[] a) {
        return a.length >= 3 & a[2] == 3;
    }

    public static void instanceofEx() {
        String s = null;
        System.out.println(s instanceof String);
        Date date = new Date();
//        System.out.println(date instanceof  String);
        date = (Date) new Object();

        //String对象是否是Object的实例
        boolean b1 = "Sting" instanceof Object;
//String对象是否是String的实例
        boolean b2 = new String() instanceof String;
//Object对象是否是String的实例
        boolean b3 = new Object() instanceof String;
//拆箱类型是否是装箱类型的实例
//        boolean b4 = 'A' instanceof Character;
//空对象是否是String的实例
        boolean b5 = null instanceof String;
//类型转换后的空对象是否是String的实例
        boolean b6 = (String) null instanceof String;
    }

    public static void bigProblem() {
        BigInteger fiveThousand = new BigInteger("5000");
        BigInteger fiftyThousand = new BigInteger("50000");
        BigInteger fiveHundredThousand = new BigInteger("500000");
        BigInteger total = BigInteger.ZERO;
        total.add(fiveThousand);
        total.add(fiftyThousand);
        total.add(fiveHundredThousand);
        System.out.println(total);
    }

    public static void difference() {
        int vals[] = {789, 678, 567, 456, 345, 234, 123, 012};
        Set diffs = new HashSet();
        for (int i = 0; i < vals.length; i++)
            for (int j = i; j < vals.length; j++)
                diffs.add(vals[i] - vals[j]);
        System.out.println(diffs.size());
    }

    public static void datingGame() {
        Calendar cal = Calendar.getInstance();
        cal.set(1999, 12, 31); // Year, Month,Day
        System.out.print(cal.get(Calendar.YEAR) + " ");
        Date d = cal.getTime();
        System.out.println(d.getDay());
    }
}

