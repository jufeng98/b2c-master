package com.javamaster.b2c.cloud.test.boot;

import com.javamaster.b2c.cloud.test.boot.aspect.LogAspect;
import com.javamaster.b2c.cloud.test.boot.model.Address;
import com.javamaster.b2c.cloud.test.boot.model.Person;
import com.javamaster.b2c.cloud.test.boot.service.*;
import com.javamaster.b2c.cloud.test.boot.service.impl.BookServiceImpl;
import com.javamaster.b2c.cloud.test.boot.validator.PersonValidator;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class SpringTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/b2c-servlet.xml");
        Object home = context.getBean("homeController");
        System.out.println(home);

        BookHelper bookHelper = context.getBean(BookHelper.class);
        System.out.println(bookHelper);

        BookUtils bookUtils = context.getBean(BookUtils.class);
        System.out.println(bookUtils);

        Resource resource = context.getResource("classpath:application.yml");
        System.out.println(resource.exists());
    }


    @Test
    public void test1() {
        GenericApplicationContext context1 = new GenericApplicationContext();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(context1);
        xmlBeanDefinitionReader.loadBeanDefinitions("spring/b2c-servlet.xml");
        context1.refresh();

        Object home = context1.getBean("beanCycle");
        System.out.println(home);
    }

    @Test
    public void test2() {
        Person person = new Person();
        BeanWrapper beanWrapper = new BeanWrapperImpl(person);
        beanWrapper.setPropertyValue("name", "jufeng98");

        Object name = beanWrapper.getPropertyValue("name");
        System.out.println(name);

        Address address = new Address();
        address.setCity("guangzhou");
        person.setAddress(address);

        Object city = beanWrapper.getPropertyValue("address.city");
        System.out.println(city);

        GenericConversionService conversionService = new GenericConversionService();
        conversionService.addConverter((Converter<String, Integer>) source -> Integer.valueOf(source));
        boolean b = conversionService.canConvert(String.class, int.class);
        System.out.println(b);

        DataBinder dataBinder = new DataBinder(new Person());
        dataBinder.setValidator(new PersonValidator());
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "jufeng98");
        propertyValues.add("age", -2);
        dataBinder.bind(propertyValues);
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);
    }

    @Test
    public void test3() {
        BookServiceImpl bookServiceImpl = new BookServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(bookServiceImpl);
        proxyFactory.setExposeProxy(true);
        proxyFactory.addInterface(BookService.class);
        proxyFactory.addAdvice((MethodInterceptor) invocation -> {
            Object[] arguments = invocation.getArguments();
            if (arguments.length == 0) {
                return invocation.proceed();
            }
            arguments[0] = (arguments[0] + "").toUpperCase();
            return invocation.proceed();
        });
        BookService bookServiceProxy = (BookService) proxyFactory.getProxy();
        System.out.println(bookServiceProxy.desc("hello"));
    }

    @Test
    public void test4() {
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory();
        aspectJProxyFactory.setExposeProxy(true);
        aspectJProxyFactory.setTarget(new BookServiceImpl());
        aspectJProxyFactory.addAspect(LogAspect.class);
        BookService proxy = aspectJProxyFactory.getProxy();
        System.out.println(proxy.desc("hello"));
    }

}
