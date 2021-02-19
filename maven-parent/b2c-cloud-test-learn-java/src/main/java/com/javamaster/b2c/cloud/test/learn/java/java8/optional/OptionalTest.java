package com.javamaster.b2c.cloud.test.learn.java.java8.optional;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class OptionalTest {

    @Test
    public void test() {
        GoodPerson goodPerson = new GoodPerson();
        System.out.println(goodPerson.getCarInsuranceName());

        GoodInsurance goodInsurance = new GoodInsurance();
        goodInsurance.setName("China insurance");
        GoodCar goodCar = new GoodCar();
        goodCar.setInsurance(Optional.ofNullable(goodInsurance));
        goodPerson.setCar(Optional.ofNullable(goodCar));
        System.out.println(goodPerson.getCarInsuranceName());
    }

    @Test
    public void test1() {
        Person person = new Person();
        System.out.println(person.getCarInsuranceNameSafe1());
        System.out.println(person.getCarInsuranceNameSafe2());
        System.out.println(person.getCarInsuranceNameSafe3());
        System.out.println(person.getCarInsuranceNameSafe4());
        System.out.println(person.findInsuranceByName("pingan").isPresent());
        System.out.println(person.getCarInsuranceName());
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap(10);
        map.put("a", "hello");
        map.put("bb", "world");
        System.out.println(OptionalUtils.getValue(map.get("b")).orElse(""));
        System.out.println(OptionalUtils.getValue(map.get("bb")).orElse(""));

        System.out.println(OptionalUtils.stringToInt("12").orElseThrow(() -> new RuntimeException("100000-输入有误,请重新输入")));
        System.out.println(OptionalUtils.stringToInt("12a").orElseThrow(() -> new RuntimeException("100000-输入有误,请重新输入")));
    }

}

