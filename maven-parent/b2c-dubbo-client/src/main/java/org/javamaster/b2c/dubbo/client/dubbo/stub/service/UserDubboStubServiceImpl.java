package org.javamaster.b2c.dubbo.client.dubbo.stub.service;

import org.apache.dubbo.rpc.service.EchoService;
import org.javamaster.b2c.dubbo.server.api.dto.UserDto;
import org.javamaster.b2c.dubbo.server.api.service.UserDubboService;

import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/9/17
 */
public class UserDubboStubServiceImpl implements UserDubboService, EchoService {

    private UserDubboService userDubboService;

    public UserDubboStubServiceImpl(UserDubboService userDubboService) {
        this.userDubboService = userDubboService;
    }

    @Override
    public List<UserDto> queryAll() {
        return userDubboService.queryAll();
    }

    @Override
    public Map<String, Object> query(List<Integer> empCodes) {
        return userDubboService.query(empCodes);
    }

    @Override
    public UserDto find(UserDto userDto) {
        return userDubboService.find(userDto);
    }

    @Override
    public Object $echo(Object message) {
        return ((EchoService) userDubboService).$echo(message);
    }
}
