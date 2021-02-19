package com.javamaster.b2c.cloud.test.pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created on 2018/10/13.<br/>
 *
 * @author yudong
 */
//当属性有变更时,调用/refresh接口会刷新属性,从而得到变更后的属性值
@RefreshScope
@RestController
@RequestMapping("/greenmail")
public class RefreshPropController {

    @Value("${greenmail.company}")
    private String company;


    @GetMapping("/company")
    public String test() {
        System.out.println("the value is:" + company);
        return company;
    }

}
