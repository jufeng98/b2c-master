package com.javamaster.b2c.cloud.test.learn.java.java8.strategy;

import org.junit.Test;

/**
 * Created on 2019/5/8.
 *
 * @author yudong
 */
public class ValidateTest {
    @Test
    public void test() {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        System.out.println(b1);

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println(b2);

        numericValidator = new Validator((s) -> s.matches("\\d+"));
        b1 = numericValidator.validate("aaaa");
        System.out.println(b1);

        lowerCaseValidator = new Validator((s) -> s.matches("[a-z]+"));
        b2 = lowerCaseValidator.validate("bbbb");
        System.out.println(b2);
    }
}
