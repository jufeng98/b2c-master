package com.javamaster.b2c.cloud.test.boot.factory;

import com.javamaster.b2c.cloud.test.boot.service.AccountService;
import com.javamaster.b2c.cloud.test.boot.service.ClientService;

/**
 * @author yudong
 * @date 2022/4/8
 */
public class DefaultServiceLocator {
    private static final ClientService clientService = new ClientService();
    private static final AccountService accountService = new AccountService();

    public ClientService createClientServiceInstance() {
        return clientService;
    }

    public AccountService createAccountServiceInstance() {
        return accountService;
    }
}
