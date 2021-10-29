package org.javamaster.get.ip;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2020/6/18
 */
public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> {
        try {
            return new String(Files.readAllBytes(Paths.get("C:\\Users\\yu\\Nox_share\\ImageShare\\hosts.txt")));
        } catch (IOException e) {
            logger.severe(e.getClass() + " " + e.getMessage());
            return "";
        }
    });

    public static void main(String[] args) throws Exception {
        while (true) {
            StringBuilder content = new StringBuilder();
            content.append("127.0.0.1     localhost\n");
            content.append("::1           ip6-localhost\n");
            Map<String, String> ipMap = new HashMap<>(5, 1);
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual()) {
                    continue;
                }
                if (!netInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!(inetAddress instanceof Inet4Address)) {
                        continue;
                    }
                    Inet4Address inet4Address = (Inet4Address) inetAddress;
                    ipMap.put(netInterface.getName(), inet4Address.getHostAddress());
                }
            }

            for (Map.Entry<String, String> stringEntry : ipMap.entrySet()) {
                content.append("#").append(stringEntry.getKey()).append("\n");
                content.append(stringEntry.getValue()).append("      agent.javamaster.org\n");
                logger.info("system ip is:" + stringEntry.getKey() + " " + stringEntry.getValue());
            }

            FileWriter fileWriter = null;
            try {
                File file = new File("C:\\Users\\yu\\Nox_share\\ImageShare\\hosts.txt");
                String oldHostsContent = threadLocal.get();
                String hostContent = content.toString();
                if (!oldHostsContent.equals(hostContent)) {
                    fileWriter = new FileWriter(file);
                    fileWriter.write(hostContent);
                    threadLocal.set(hostContent);
                    logger.info("write hosts info to " + file.getAbsolutePath() + " finished");
                    showTray(hostContent);
                }
            } catch (Exception e) {
                logger.severe(e.getClass() + " " + e.getMessage());
            } finally {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            }
            TimeUnit.SECONDS.sleep(10);
        }
    }

    private static void showTray(String msg) throws Exception {
        Toolkit.getDefaultToolkit().beep();
        SystemTray systemTray = SystemTray.getSystemTray();
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("favicon.png");
        BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(stream));
        TrayIcon trayIcon = new TrayIcon(bufferedImage);
        systemTray.add(trayIcon);
        trayIcon.displayMessage("通知", msg, TrayIcon.MessageType.INFO);
    }

}
