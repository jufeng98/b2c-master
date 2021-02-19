package org.javamaster.b2c.swagger2.service.impl;

import org.javamaster.b2c.swagger2.model.User;
import org.javamaster.b2c.swagger2.model.UserReqVo;
import org.javamaster.b2c.swagger2.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author yudong
 * @date 2020/5/27
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${user.enabled}")
    private boolean enabled;

    @Value("${user.desc}")
    private String desc;

    @Override
    public User login(UserReqVo userReqVo) {
        return user(userReqVo);
    }

    @Override
    public User login(UserReqVo userReqVo, String appType) {
        User user = user(userReqVo);
        user.setDesc(user.getDesc() + " " + appType);
        return user;
    }

    @Override
    public User login(UserReqVo userReqVo, Integer appType) {
        User user = user(userReqVo);
        user.setDesc(user.getDesc() + " " + appType);
        return user;
    }

    private User user(UserReqVo userReqVo) {
        User user = new User();
        user.setUserId("100000001");
        user.setUsername(userReqVo.getUsername());
        user.setEnabled(enabled);
        user.setDesc(desc);
        return user;
    }

}
