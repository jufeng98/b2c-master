package org.javamaster.spring.test.config;

import static org.javamaster.spring.test.GeneralTestCode.PROFILE_UNIT_TEST;
import org.javamaster.spring.test.advisor.FeignBeanUrlAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * 修改feign service的url以便用于能正常调用
 *
 * @author yudong
 * @date 2021/5/13
 */
@SuppressWarnings("all")
@TestConfiguration
@EnableFeignClients({
        "cn.com.bluemoon"
})
@ImportAutoConfiguration({
        RibbonAutoConfiguration.class,
        FeignRibbonClientAutoConfiguration.class,
        FeignAutoConfiguration.class
})
@Profile(PROFILE_UNIT_TEST)
@EnableAspectJAutoProxy
public class FeignTestConfig {
    @Autowired
    private ApplicationContext context;

    @Bean
    public FeignBeanUrlAdvisor feignBeanUrlAdvisor() {
        return new FeignBeanUrlAdvisor();
    }

}
