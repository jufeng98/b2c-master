package org.javamaster.b2c.dubbo.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.EchoService;
import org.javamaster.b2c.dubbo.server.api.dto.UserDto;
import org.javamaster.b2c.dubbo.server.api.service.UserDubboService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2018/10/11.<br/>
 *
 * @author yudong
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @DubboReference(version = "1.0.0", timeout = 6000,
            stub = "org.javamaster.b2c.dubbo.client.dubbo.stub.service.UserDubboStubServiceImpl")
    private UserDubboService userDubboService;

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        List<UserDto> users = userDubboService.queryAll();
        log.info(users.toString());
        return users;
    }


    @PostMapping("/find")
    public UserDto find(@RequestBody UserDto userDto) {
        // 测试服务是否可用
        EchoService echoService = (EchoService) userDubboService;
        log.info("{}", echoService.$echo("OK"));
        RpcContext rpcContext = RpcContext.getContext();
        log.info("{}", rpcContext);
        rpcContext.setAttachment("age", 20);
        return userDubboService.find(userDto);
    }

}
