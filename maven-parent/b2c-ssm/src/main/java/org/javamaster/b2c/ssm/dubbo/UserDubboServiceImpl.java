package org.javamaster.b2c.ssm.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.javamaster.b2c.dubbo.server.api.dto.UserDto;
import org.javamaster.b2c.dubbo.server.api.service.UserDubboService;

import java.util.*;

@Service(version = "1.0.0", timeout = 6000)
public class UserDubboServiceImpl implements UserDubboService {
    @Override
    public List<UserDto> queryAll() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, Object> query(List<Integer> list) {
        return new HashMap<>();
    }

    @Override
    public UserDto find(UserDto userDto) {
        return new UserDto();
    }
}
