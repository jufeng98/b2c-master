package org.javamaster.b2c.jmx.runner;

import org.javamaster.b2c.jmx.model.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jmx.export.annotation.*;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;

import javax.management.Notification;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2020/8/19
 */
@Component
@ManagedResource(objectName = "org.javamaster.b2c:mbean=SendJmxRunner")
@ManagedNotification(notificationTypes = "actor", name = "runner")
public class SendJmxRunner implements NotificationPublisherAware, CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getName());

    private NotificationPublisher notificationPublisher;
    private Random random = new Random();

    private String name = "Jack";

    @ManagedAttribute
    public String getName() {
        return name;
    }

    @ManagedAttribute
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @Override
    public void run(String... args) {
        new Thread(() -> {
            while (true) {
                Actor actor = new Actor();
                actor.setActorId(random.nextInt());
                actor.setFirstName(name);
                actor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                notificationPublisher.sendNotification(new Notification("actor", actor, 0));
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ignored) {
                }
            }
        }).start();
        logger.info("pid is:{}", ManagementFactory.getOperatingSystemMXBean().getName().split("@")[0]);
    }

}
