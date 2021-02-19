package com.javamaster.b2c.jsp.service;


import com.javamaster.b2c.jsp.model.Account;

/**
 * Created on 2018/9/15.<br/>
 *
 * @author yudong
 */
public interface UserService {
    void signup(Account account);

    void activate(Account account);
}
