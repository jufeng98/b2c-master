package com.javamaster.b2c.cloud.test.learn.java.highquality;

import com.javamaster.b2c.cloud.test.learn.java.enums.SeasonEnum;

import java.util.EnumSet;

/**
 * Created on 2019/5/8.
 *
 * @author yudong
 */
public class SeasonEnumTest {

    public static void main(String[] args) {
        SeasonEnum spring = SeasonEnum.SPRING;
        System.out.println(spring.ordinal());
        for (SeasonEnum season : SeasonEnum.values()) {
            System.out.println(season.toString());
        }
        SeasonEnum winter = SeasonEnum.valueOf("WINTER");
        System.out.println(winter);
        EnumSet<SeasonEnum> enumSet = EnumSet.allOf(SeasonEnum.class);
        System.out.println(enumSet.size());
    }

}
