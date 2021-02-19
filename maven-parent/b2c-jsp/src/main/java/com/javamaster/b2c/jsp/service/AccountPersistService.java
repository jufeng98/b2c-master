package com.javamaster.b2c.jsp.service;


import com.javamaster.b2c.jsp.model.Account;

public interface AccountPersistService {
    void createAcount(Account account);

    Account readAcount(String id);

    void deleteAcount(String id);

    void updateAcount(Account account);
}
