package org.javamaster.b2c.rpc.client;

import org.javamaster.b2c.rpc.server.api.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yudong
 */
@SpringBootApplication
public class RpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcClientApplication.class, args);
    }

    /**
     * 配置RMI客户端并注册一个服务
     */
    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/BookService");
        rmiProxyFactoryBean.setServiceInterface(BookService.class);
        return rmiProxyFactoryBean;
    }

    @Bean
    public RestTemplate restTemplateOut() {
        return new RestTemplate();
    }

}
