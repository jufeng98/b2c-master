package org.javamaster.b2c.rpc.server;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.javamaster.b2c.rpc.server.api.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * @author yudong
 */
@SpringBootApplication
public class RpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcServerApplication.class, args);
    }

    /**
     * 配置RMI服务器端并暴露RMI服务
     */
    @Bean
    public RmiServiceExporter rmiServiceExporter(BookService bookService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(bookService);
        exporter.setServiceName("BookService");
        exporter.setServiceInterface(BookService.class);
        // 默认会尝试绑定到本地localhost的1099端口上的RMI注册表,如果没有发现RMI注册表,那么就会启动一个注册表
        // 如果希望绑定到其他主机的注册表,那么设置以下两个属性即可
        //exporter.setRegistryHost("localhost");
        //exporter.setRegistryPort(1099);
        return exporter;
    }

    /**
     * JAX-WS服务器端配置,独立占用一个8080端口
     */
    @Bean
    public SimpleJaxWsServiceExporter jaxWsServiceExporter() {
        SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
        exporter.setBaseAddress("http://localhost:8080/rpc/services/");
        return exporter;
    }

    /**
     * JAX-RS配置
     */
    @Bean
    public ServletRegistrationBean<ServletContainer> jersetServlet() {
        // our rest resources will be available in the path /jersey/*
        ServletRegistrationBean<ServletContainer> registration = new ServletRegistrationBean<>(new ServletContainer(), "/jersey/*");
        registration.addInitParameter("javax.ws.rs.Application", JerseyConfig.class.getName());
        return registration;
    }

}
