package org.javamaster.b2c.dubbo.server.service.impl;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.javamaster.b2c.dubbo.server.api.dto.UserDto;
import org.javamaster.b2c.dubbo.server.api.service.UserDubboService;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 2018/10/11.<br/>
 *
 * @author yudong
 */
@Slf4j
@DubboService(version = "1.0.0", validation = "true")
public class UserDubboServiceImpl implements UserDubboService {

    private final AtomicInteger atomicInteger = new AtomicInteger();

    @SneakyThrows
    @Override
    public List<UserDto> queryAll() {
        log.info("dubbo thread name:{},id:{},count:{}",
                Thread.currentThread().getName(),
                Thread.currentThread().getId(),
                atomicInteger.incrementAndGet());
        List<UserDto> users = new ArrayList<>(3);
        users.add(new UserDto(1L, "yu", "123yu", 23));
        users.add(new UserDto(2L, "yudong", "123yudong", 24));
        users.add(new UserDto(2L, "liangyudong", "123liangyudong", 25));
        return users;
    }

    @Override
    public Map<String, Object> query(List<Integer> empCodes) {
        Map<String, Object> empInfo1 = new HashMap<>(10);
        empInfo1.put("empCode", 80526611);
        empInfo1.put("havaQuit", 1);
        Map<String, Object> empInfo2 = new HashMap<>();
        empInfo2.put("empCode", 80231671);
        empInfo2.put("havaQuit", 1);
        Map<String, Object> empInfo3 = new HashMap<>();
        empInfo3.put("empCode", 80546269);
        empInfo3.put("havaQuit", 0);
        List<Object> list = new ArrayList<>();
        list.add(empInfo1);
        list.add(empInfo2);
        list.add(empInfo3);
        Map<String, Object> map = new HashMap<>(10);
        map.put("list", list);
        return map;
    }

    @Override
    public UserDto find(@NotNull UserDto userDto) {
        RpcContext rpcContext = RpcContext.getContext();
        log.info("{},{}", rpcContext.isProviderSide(), rpcContext.getAttachment("age"));
        return userDto;
    }
}
