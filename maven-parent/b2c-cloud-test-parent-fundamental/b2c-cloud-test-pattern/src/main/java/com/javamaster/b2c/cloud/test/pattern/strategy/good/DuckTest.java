package com.javamaster.b2c.cloud.test.pattern.strategy.good;

/**
 * 策略模式:定义了算法簇,分别封装起来,让彼此之间可以相互替换,使得算法的变化独立于使用算法的客户
 * <p>
 * Created on 2018/6/29.
 *
 * @author yudong
 */
public class DuckTest {
    public static void main(String[] args) {
        Duck duck = new YellowDuck();
        duck.setFlyable(new FlyWithWings());
        duck.setQuarkable(new QuarkMute());
        duck.fly();
        duck.peformQuack();
        duck.peformSwim();
    }
}
