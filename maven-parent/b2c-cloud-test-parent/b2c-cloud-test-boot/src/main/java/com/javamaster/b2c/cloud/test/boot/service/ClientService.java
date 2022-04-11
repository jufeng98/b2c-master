package com.javamaster.b2c.cloud.test.boot.service;

/**
 * @author yudong
 * @date 2022/4/8
 */
public class ClientService {
    private static final ClientService clientService = new ClientService();

    public static ClientService getInstance() {
        return clientService;
    }
}
