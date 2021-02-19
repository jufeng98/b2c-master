package org.javamaster.b2c.dubbo.client;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.*;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.javamaster.b2c.dubbo.server.api.dto.UserDto;
import org.javamaster.b2c.dubbo.server.api.service.UserDubboService;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author yudong
 * @date 2020/9/16
 */
@Slf4j
public class DubboReferenceMainTests {

    @SneakyThrows
    @Test
    public void consumerService() {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-client-independent");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(B2cMasterConsts.Dubbo.ZOOKEEPER_ADDRESS + "?timeout=30000");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

        // 引用远程服务
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<UserDubboService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        // 多个注册中心可以用setRegistries()
        reference.setRegistry(registry);
        reference.setInterface(UserDubboService.class);
        reference.setVersion("1.0.0");
        reference.setTimeout(10000);
        reference.setRetries(0);

        // 和本地bean一样使用xxxService
        // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        UserDubboService userDubboService = reference.get();
        List<UserDto> userDtos = userDubboService.queryAll();
        log.info("queryAll:{}", userDtos);
    }

}
