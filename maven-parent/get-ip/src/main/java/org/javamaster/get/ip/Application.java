package org.javamaster.get.ip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2020/6/18
 */
public class Application {

    static Logger logger = Logger.getLogger(Application.class.getName());

    static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> {
        try {
            return new String(Files.readAllBytes(Paths.get("C:\\Users\\yu\\Nox_share\\ImageShare\\hosts.txt")));
            // return new String(Files.readAllBytes(Paths.get("D:\\User\\天天共享文件夹\\hosts.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    });

    public static void main(String[] args) throws Exception {
        while (true) {
            StringBuilder content = new StringBuilder();
            content.append("127.0.0.1     localhost\n");
            content.append("::1           ip6-localhost\n");
            Map<String, String> ipMap = new HashMap<>(2, 1);
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
                    ipMap.put(netInterface.getName(), inetAddress.getHostAddress());
                }
            }
            String ip = "";
            for (Map.Entry<String, String> stringEntry : ipMap.entrySet()) {
                if (stringEntry.getKey().contains("eth")) {
                    ip = stringEntry.getValue();
                    break;
                }
                ip = stringEntry.getValue();
            }

            content.append(ip).append("      agent.javamaster.org\n");
            FileWriter fileWriter = null;
            FileWriter fileWriter1 = null;
            try {
                File file = new File("C:\\Users\\yu\\Nox_share\\ImageShare\\hosts.txt");
                File file1 = new File("D:\\User\\天天共享文件夹\\hosts.txt");

                String oldHostsContent = threadLocal.get();
                if (!oldHostsContent.equals(content.toString())) {
                    fileWriter = new FileWriter(file);
                    fileWriter1 = new FileWriter(file1);
                    fileWriter.write(content.toString());
                    fileWriter1.write(content.toString());
                    threadLocal.set(content.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    fileWriter.close();
                }
                if (fileWriter1 != null) {
                    fileWriter1.close();
                }
            }
            logger.info(ip);
            TimeUnit.SECONDS.sleep(10);
        }

    }

}
