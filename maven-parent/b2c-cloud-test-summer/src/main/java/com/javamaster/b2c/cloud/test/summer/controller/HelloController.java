package com.javamaster.b2c.cloud.test.summer.controller;

import com.javamaster.b2c.cloud.test.summer.helper.HelloHelper;
import com.javamaster.b2c.cloud.test.summer.model.SayHelloReqVo;
import com.javamaster.b2c.cloud.test.summer.service.HelloService;
import org.summerframework.summer.core.anno.SummerAutowired;
import org.summerframework.summer.web.anno.SummerGetMapping;
import org.summerframework.summer.web.anno.SummerRequestBody;
import org.summerframework.summer.web.anno.SummerRestfulController;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;


/**
 * @author yudong
 * @date 2019/5/13
 */
@SummerRestfulController(urlPrefix = "/hello")
public class HelloController {
    private Logger logger = Logger.getLogger(getClass().getName());

    @SummerAutowired("helloServiceImpl")
    private HelloService helloService;

    @SummerAutowired
    private HelloHelper helloHelper;

    @PostConstruct
    private void init() {
        logger.info("PostConstruct init called,bean method result:" + this.sayHello(new SayHelloReqVo()));
    }

    @SummerGetMapping("/sayHello")
    private String sayHello(@SummerRequestBody SayHelloReqVo reqVo) {
        return helloService.sayHello() + " " + helloHelper.getWelcome() + reqVo.toString();
    }
}
