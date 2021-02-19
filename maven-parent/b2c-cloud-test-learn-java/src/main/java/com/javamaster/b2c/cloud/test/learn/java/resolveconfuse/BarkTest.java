package com.javamaster.b2c.cloud.test.learn.java.resolveconfuse;

/**
 * @author yu
 * @date 2018/4/19
 */
public class BarkTest {

    public static void main(String args[]) {
        Dogg woofer = new Dogg();
        Dogg nipper = new Basenji();
        woofer.bark();
        nipper.bark();
    }
}

class Dogg {
    public static void bark() {
        System.out.print("woof ");
    }
}

class Basenji extends Dogg {
    public static void bark() {
    }
}


