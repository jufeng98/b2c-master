package org.javamaster.get.ip;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2020/6/18
 */
public class Application {
    static {
        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        logger.info(ManagementFactory.getRuntimeMXBean().getName());
        try {
            // ClipboardListener.startListener();

            MonitorNetwork.startMonitor();

            // SendMsg.start();
        } catch (Exception e) {
            logger.severe(e.getClass() + " " + e.getMessage());
        }

        try {
            TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            logger.severe(e.getClass() + " " + e.getMessage());
        }
    }

}
