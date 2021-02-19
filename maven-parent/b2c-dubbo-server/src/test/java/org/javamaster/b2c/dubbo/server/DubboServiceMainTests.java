package org.javamaster.b2c.dubbo.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.*;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.javamaster.b2c.dubbo.server.api.service.UserDubboService;
import org.javamaster.b2c.dubbo.server.service.impl.UserDubboServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
@Slf4j
public class DubboServiceMainTests {

    @Test
    public void startService() throws Exception {
        // 服务实现
        UserDubboService userDubboService = new UserDubboServiceImpl();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-server-independent");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(B2cMasterConsts.Dubbo.ZOOKEEPER_ADDRESS);

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(25222);
        protocol.setThreads(200);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<UserDubboService> service = new ServiceConfig<>();
        service.setApplication(application);
        // 多个注册中心可以用setRegistries()
        service.setRegistry(registry);
        // 多个协议可以用setProtocols()
        service.setProtocol(protocol);
        service.setInterface(UserDubboService.class);
        service.setRef(userDubboService);
        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();
        log.info("start service success");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

}
