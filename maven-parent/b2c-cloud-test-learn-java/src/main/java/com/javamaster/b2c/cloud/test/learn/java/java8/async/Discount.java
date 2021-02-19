package com.javamaster.b2c.cloud.test.learn.java.java8.async;

import java.text.DecimalFormat;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        Shop.delay();
        DecimalFormat dFormat = new DecimalFormat("#.00");
        String string = dFormat.format(price * (100 - code.percentage) / 100);
        double temp = Double.parseDouble(string);
        return temp;

    }
}
