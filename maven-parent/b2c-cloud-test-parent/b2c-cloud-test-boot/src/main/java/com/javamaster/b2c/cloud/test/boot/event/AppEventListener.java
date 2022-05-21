package com.javamaster.b2c.cloud.test.boot.event;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * @author yudong
 * @date 2022/5/1
 */
@Component
public class AppEventListener {

    @EventListener(ContextRefreshedEvent.class)
    public void complete(ContextRefreshedEvent event) {
        System.out.println(event);
    }

    @EventListener(RequestHandledEvent.class)
    public void complete(RequestHandledEvent event) {
        System.out.println(event);
    }
}
