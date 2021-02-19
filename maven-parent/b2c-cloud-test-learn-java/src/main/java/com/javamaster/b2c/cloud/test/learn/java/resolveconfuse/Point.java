package com.javamaster.b2c.cloud.test.learn.java.resolveconfuse;

/**
 * @author yudong
 * @date 2018/4/19
 */
public class Point {
    protected final int x, y;
    private final String name; // Cached at construction time

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        name = makeName();
    }

    protected String makeName() {
        return "[" + x + "," + y + "]";
    }

    public final String toString() {
        return name;
    }
}

class ColorPoint extends Point {
    private final String color;

    ColorPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    protected String makeName() {
        return super.makeName() + ":" + color;
    }
}

class PointTest {
    public static void main(String[] args) {
        System.out.println(new ColorPoint(4, 2, "purple"));
    }
}