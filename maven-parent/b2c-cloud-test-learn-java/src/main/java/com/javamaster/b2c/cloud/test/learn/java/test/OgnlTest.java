package com.javamaster.b2c.cloud.test.learn.java.test;

import com.alibaba.fastjson.JSON;
import com.javamaster.b2c.cloud.test.learn.java.model.Person;
import com.javamaster.b2c.cloud.test.learn.java.model.Person1;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlContext;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author yudong
 * @date 2020/10/15
 */
@Slf4j
public class OgnlTest {

    @Test
    @SneakyThrows
    public void test() {
        OgnlContext context = new OgnlContext();
        Object expression = Ognl.parseExpression("@java.lang.System@out.println('hello world')");
        Object value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("@java.lang.Math@random()");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("@java.lang.Thread@currentThread()");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("@java.lang.Thread@currentThread().getContextClassLoader()");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("@java.lang.System@out");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);
    }

    @Test
    @SneakyThrows
    public void test1() {
        OgnlContext context = new OgnlContext();
        Object expression = Ognl.parseExpression("#value1=@System@getProperty('java.home'), #value2=@System@getProperty('java.runtime.name'), {#value1, #value2}");
        Object value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("'hello world'.length()");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("new int[] { 1, 2, 3 }");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", value, value.getClass().getName());

        expression = Ognl.parseExpression("#{ 'foo' : 'foo value', 'bar' : 'bar value' }");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", value, value.getClass().getName());

        expression = Ognl.parseExpression("#@java.util.LinkedHashMap@{ 'foo' : 'foo value', 'bar' : 'bar value' }");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", value, value.getClass().getName());

        expression = Ognl.parseExpression("#value1=new Person1(),#value2=#value1.firstName,{#value1,#value2}");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("#value1=new Person1(),#value2=#value1.getFirstName(),{#value2}");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("#value1=new Person1(),#value1.setFirstName('Jack'),{#value1}");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);

        expression = Ognl.parseExpression("#value1=new Person1(),#value1.firstName='Jack',{#value1}");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", value);
    }

    @Test
    @SneakyThrows
    public void test2() {
        Person1 person1 = new Person1();
        person1.setFirstName("Jack");
        person1.setLastName("Dodson");

        Person person = new Person();
        person.setName("liangyudong");
        person.setPersonId(100001L);

        OgnlContext context = new OgnlContext();
        context.put("person", person);
        context.setRoot(person1);

        Object expression = Ognl.parseExpression("#person.name");
        Object name = Ognl.getValue(expression, context, context.getRoot());
        log.info("{}", name);

        Object expression1 = Ognl.parseExpression("lastName");
        Object lastName = Ognl.getValue(expression1, context, context.getRoot());
        log.info("{}", lastName);

        expression = Ognl.parseExpression("lastName in { null,'Untitled' }");
        Object value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", value, value.getClass().getName());
    }

    @Test
    @SneakyThrows
    public void test3() {
        Person person = new Person();
        person.setName("liangyudong");
        person.setPersonId(101L);

        Person person1 = new Person();
        person1.setName("jufeng98");
        person1.setPersonId(201L);

        Person person2 = new Person();
        person2.setName("Jack");
        person2.setPersonId(301L);

        val persons = new ArrayList<Person>();
        persons.add(person);
        persons.add(person1);
        persons.add(person2);

        OgnlContext context = new OgnlContext();
        context.put("persons", persons);

        // 过滤
        Object expression = Ognl.parseExpression("#persons.{? #this.personId > 200}");
        Object value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", JSON.toJSONString(value, true), value.getClass().getName());

        // 过滤后留下第一个结果
        expression = Ognl.parseExpression("#persons.{^ #this.personId > 200}");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", JSON.toJSONString(value, true), value.getClass().getName());

        // 过滤后留下最后一个结果
        expression = Ognl.parseExpression("#persons.{$ #this.personId > 200}");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", JSON.toJSONString(value, true), value.getClass().getName());

        // 投影
        expression = Ognl.parseExpression("#persons.{#this.name}");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", JSON.toJSONString(value, true), value.getClass().getName());

        expression = Ognl.parseExpression("#persons.size().(#this > 100? 2*#this : 20+#this)");
        value = Ognl.getValue(expression, context, context.getRoot());
        log.info("{},{}", JSON.toJSONString(value, true), value.getClass().getName());
    }
}
