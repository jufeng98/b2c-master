package com.javamaster.b2c.cloud.test.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javamaster.b2c.cloud.test.common.model.*;
import com.javamaster.b2c.cloud.test.user.mapper.UsersMapper;
import com.javamaster.b2c.cloud.test.user.service.UserService;
import com.javamaster.b2c.cloud.test.user.vo.UpdatePasswordReqVo;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;

/**
 * Created on 2018/12/9.<br/>
 *
 * @author yudong
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Pair<String, UserDetailBean> login(Users users) {
        final Authentication authentication = authenticationManager.authenticate(
                new TestingAuthenticationToken(userDetailsService.loadUserByUsername(users.getUsername()), users.getPassword()));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        UserDetailBean userDetailBean = (UserDetailBean) authentication.getPrincipal();
        String keyPrefix = "spring:user:sessions:";
        return new Pair<>(keyPrefix + UUID.randomUUID().toString(), userDetailBean);
    }

    @Override
    public Users findUsersByUsername(String username) {
        Users users = Users.getInstance().username(username);
        return usersMapper.selectOne(users);
    }

    @Override
    public PageInfo<Users> findUsers(Users users) {
        Page page = users.getPage();
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        List<Users> list = usersMapper.select(users);
        PageInfo<Users> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Users createUsers(Users users) {
        users.setEnabled(true);
        users.setFirstLogin(true);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersMapper.insert(users);
        return usersMapper.selectOne(users);
    }

    @Override
    public Integer disabledUsers(Users users) {
        users.setEnabled(false);
        int affectRows = usersMapper.updateByPrimaryKeySelective(users);
        Assert.isTrue(affectRows != 0, "10002:关闭失败,用户不存在");
        return affectRows;
    }

    @Override
    public Integer enabledUsers(Users users) {
        users.setEnabled(true);
        int affectRows = usersMapper.updateByPrimaryKeySelective(users);
        Assert.isTrue(affectRows != 0, "10002:启用失败,用户不存在");
        return affectRows;
    }

    @Override
    public Integer updatePassword(UpdatePasswordReqVo reqVo) {

        Users dbUsers = findUsersByUsername(reqVo.getUsername());
        Assert.notNull(dbUsers, "10004:更新失败,用户不存在");
        Assert.isTrue(passwordEncoder.matches(reqVo.getPassword(), dbUsers.getPassword())
                , "10001:旧密码不正确");
        dbUsers.setPassword(passwordEncoder.encode(reqVo.getNewPassword()));
        return usersMapper.updateByPrimaryKey(dbUsers);
    }

}
