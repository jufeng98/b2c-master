package com.javamaster.b2c.cloud.test.boot;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javamaster.b2c.cloud.test.boot.controller.ComputeController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {BootApplication.class})
public class BootTest {
    @Autowired
    private ComputeController controller;
    private static MockHttpServletRequest request;

    @BeforeClass
    public static void init() {
        request = new MockHttpServletRequest();
        request.setContentType("application/json");
    }

    @Test
    public void testAdd() {
        System.out.println(controller.add(4, 5, request));
    }
}
