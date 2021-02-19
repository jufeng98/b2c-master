package org.javamaster.b2c.swagger2.service;

import org.javamaster.b2c.swagger2.model.User;
import org.javamaster.b2c.swagger2.model.UserReqVo;

/**
 * @author yudong
 * @date 2020/5/27
 */
public interface LoginService {
    User login(UserReqVo userReqVo);

    User login(UserReqVo userReqVo, String appType);

    User login(UserReqVo userReqVo, Integer appType);
}
