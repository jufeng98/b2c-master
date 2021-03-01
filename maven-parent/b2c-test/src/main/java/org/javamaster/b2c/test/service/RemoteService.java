package org.javamaster.b2c.test.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author yudong
 * @date 2021/2/26
 */
@Component
@FeignClient(name = "b2c-user")
@RequestMapping("/user")
public interface RemoteService {

    @ResponseBody
    @PostMapping("/getUserNameByMobile")
    String getUserMobileById(Integer id);

}
