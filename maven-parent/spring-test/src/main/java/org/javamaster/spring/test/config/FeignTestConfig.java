package org.javamaster.spring.test.config;

import static org.javamaster.spring.test.GeneralTestCode.PROFILE_UNIT_TEST;
import org.javamaster.spring.test.utils.TestUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

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
public class FeignTestConfig implements InitializingBean {
    @Autowired
    private ApplicationContext context;
    @Value("${feign.services:}")
    private String[] services;


    @Override
    public void afterPropertiesSet() throws Exception {
        for (String service : services) {
            String[] split = service.split("\\|");
            changeFeignServiceUrl(split[0], split[1]);
        }
    }

    private void changeFeignServiceUrl(String feignName, String newUrl) throws Exception {
        Object feignService = context.getBean(feignName);
        TestUtils.changeFeignBeanUrl(feignService, newUrl);
        System.out.println("begin----------------------------");
        System.out.println(feignName + " url change to " + newUrl);
        System.out.println("end------------------------------");
    }


}
