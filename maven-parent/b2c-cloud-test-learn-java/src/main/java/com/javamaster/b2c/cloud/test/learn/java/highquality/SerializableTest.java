package com.javamaster.b2c.cloud.test.learn.java.highquality;

import org.apache.commons.lang.SerializationUtils;

/**
 * Created on 2019/5/8.
 *
 * @author yudong
 */
public class SerializableTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("混世魔王");
        // 序列化，保存到磁盘上
        // 如果final属性是一个直接量，在反序列化时就会重新计算
        // 反序列化时构造函数不会执行
        // final会被重新赋值，其中的“值”指的是简单对象。简单对象包括：8个
        // 基本类型，以及数组、字符串（字符串情况很复杂，不通过new关键字生成String对象的
        // 情况下，final变量的赋值与基本类型相同），但是不能方法赋值
        // 总结一下，反序列化时final变量在以下情况下不会被重新赋值：
        // 通过构造函数为final变量赋值。
        // 通过方法返回值为final变量赋值。
        // final修饰的属性不是基本类型。
        Person person1 = (Person) SerializationUtils.deserialize(SerializationUtils.serialize(person));
        System.out.println(person1.getName());
        System.out.println(person1.getBonus());
    }
}
