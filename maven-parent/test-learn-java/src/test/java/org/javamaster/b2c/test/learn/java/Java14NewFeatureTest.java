package org.javamaster.b2c.test.learn.java;

import org.junit.Test;

import java.math.RoundingMode;

/**
 * @author yudong
 * @date 2019/10/28
 */
public class Java14NewFeatureTest {

    @Test
    public void test() {
        Object obj = "hello";
        if (obj instanceof String str) {
            System.out.println(str);
        }
    }

    @Test
    public void test1() {
        var model = RoundingMode.valueOf("DOWN");
        switch (model) {
            case UP -> System.out.println(1);
            case DOWN, FLOOR -> System.out.println(2);
            default -> System.out.println(3);
        }
    }

    @Test
    public void test2() {
        var point = new Point(12, 24);
        System.out.println(point);
    }

    @Test
    public void test3() {
        String html = """
                <html>
                    <body>
                        <p>Hello world</p>
                    </body>
                </html>
                """;
        System.out.println(html);
    }

}

