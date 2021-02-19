package org.javamaster.b2c.dubbo.server.api.service;

import org.javamaster.b2c.dubbo.server.api.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * Created on 2018/10/11.<br/>
 *
 * @author yudong
 */
public interface UserDubboService {
    List<UserDto> queryAll();

    Map<String, Object> query(List<Integer> empCodes);

    UserDto find(UserDto userDto);
}
