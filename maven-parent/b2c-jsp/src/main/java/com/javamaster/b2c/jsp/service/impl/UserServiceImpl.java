package com.javamaster.b2c.jsp.service.impl;

import com.javamaster.b2c.jsp.model.Account;
import com.javamaster.b2c.jsp.service.AccountPersistService;
import com.javamaster.b2c.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/9/15.<br/>
 *
 * @author yudong
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccountPersistService service;

    @Override
    public void signup(Account account) {
        service.createAcount(account);
    }

    @Override
    public void activate(Account account) {
        service.updateAcount(account);
    }
}
