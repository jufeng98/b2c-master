package org.javamaster.get.ip;

import static org.javamaster.get.ip.Application.showTray;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2022/4/28
 */
public class MonitorNetwork implements Runnable {
    private static final Logger logger = Logger.getLogger(Application.class.getName());
    private static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> {
        try {
            return new String(Files.readAllBytes(Paths.get("C:\\Users\\yu\\Nox_share\\ImageShare\\hosts.txt")));
        } catch (IOException e) {
            logger.severe(e.getClass() + " " + e.getMessage());
            return "";
        }
    });

    public static void startMonitor() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(new MonitorNetwork(), 0, 10, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        try {
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
                String oldHostsContent = THREAD_LOCAL.get();
                String hostContent = content.toString();
                if (!oldHostsContent.equals(hostContent)) {
                    fileWriter = new FileWriter(file);
                    fileWriter.write(hostContent);
                    THREAD_LOCAL.set(hostContent);
                    logger.info("write hosts info to " + file.getAbsolutePath() + " finished");
                    showTray(hostContent);
                }
            } finally {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            }
        } catch (Exception e) {
            logger.severe(e.getClass() + " " + e.getMessage());
        }
    }
}
