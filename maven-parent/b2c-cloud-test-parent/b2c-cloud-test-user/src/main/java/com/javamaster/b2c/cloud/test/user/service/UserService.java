package com.javamaster.b2c.cloud.test.user.service;

import com.github.pagehelper.PageInfo;
import com.javamaster.b2c.cloud.test.common.model.UserDetailBean;
import com.javamaster.b2c.cloud.test.common.model.Users;
import com.javamaster.b2c.cloud.test.user.vo.UpdatePasswordReqVo;
import javafx.util.Pair;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;

/**
 * Created on 2018/12/9.<br/>
 *
 * @author yudong
 */
@ThreadSafe
public interface UserService {

    Pair<String, UserDetailBean> login(Users users);


    @NotNull
    Users findUsersByUsername(@NotNull String username);

    PageInfo<Users> findUsers(Users users);

    Users createUsers(Users users);

    Integer disabledUsers(Users users);

    Integer enabledUsers(Users users);

    Integer updatePassword(UpdatePasswordReqVo reqVo);
}
