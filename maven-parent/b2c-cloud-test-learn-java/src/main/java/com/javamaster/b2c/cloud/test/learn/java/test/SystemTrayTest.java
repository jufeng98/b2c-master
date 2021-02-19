package com.javamaster.b2c.cloud.test.learn.java.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class SystemTrayTest {

    @Test
    @SneakyThrows
    public void test() {
        SystemTray systemTray = SystemTray.getSystemTray();
        BufferedImage bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
        PopupMenu popupMenu = new PopupMenu();
        popupMenu.add("popup menu");
        TrayIcon trayIcon = new TrayIcon(bufferedImage, "tool tip", popupMenu);
        systemTray.add(trayIcon);
        trayIcon.setImageAutoSize(true);
        trayIcon.displayMessage("hello world", "welcome to java", TrayIcon.MessageType.INFO);
    }

}
