package com.javamaster.b2c.cloud.test.learn.java.bytecodetools;

import com.javamaster.b2c.cloud.test.learn.java.model.Person;
import com.javamaster.b2c.cloud.test.learn.java.utils.OMUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

/**
 * Created on 2019/1/21.<br/>
 *
 * @author yudong
 */
@Slf4j
public class CglibTest {

    @Test
    @SneakyThrows
    public void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            log.info(String.join("", "调用", method.getName(), "方法前"));
            Object object = methodProxy.invokeSuper(o, objects);
            log.info(String.join("", "调用", method.getName(), "方法后"));
            return object;
        });
        Person person = ((Person) enhancer.create());
        person.setName("Jack");
        log.info(OMUtils.objectMapper.writeValueAsString(person));
    }

    @Test
    @SneakyThrows
    public void test1() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target");
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("name", String.class);
        beanGenerator.addProperty("age", int.class);
        Object myBean = beanGenerator.create();
        log.info(myBean.toString());
    }
}
