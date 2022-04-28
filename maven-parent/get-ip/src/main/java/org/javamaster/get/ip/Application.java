package org.javamaster.get.ip;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2020/6/18
 */
public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        try {
            ClipboardListener.startListener();

            MonitorNetwork.startMonitor();
        } catch (Exception e) {
            logger.severe(e.getClass() + " " + e.getMessage());
        }

        try {
            TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void showTray(String msg) throws Exception {
        Toolkit.getDefaultToolkit().beep();
        SystemTray systemTray = SystemTray.getSystemTray();
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("favicon.png");
        BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(stream));
        TrayIcon trayIcon = new TrayIcon(bufferedImage);
        systemTray.add(trayIcon);
        trayIcon.displayMessage("通知", msg, TrayIcon.MessageType.INFO);
    }

}
