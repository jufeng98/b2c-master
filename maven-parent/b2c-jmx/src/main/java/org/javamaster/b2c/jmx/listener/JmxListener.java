package org.javamaster.b2c.jmx.listener;

import org.javamaster.b2c.jmx.model.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * Created on 2019/4/14.<br/>
 *
 * @author yudong
 */
public class JmxListener implements NotificationListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handleNotification(Notification notification, Object handback) {
        Actor actor = ((Actor) notification.getSource());
        logger.info("接收到jmx通知:" + actor);
    }
}
