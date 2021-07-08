package com.javamaster.b2c.cloud.test.learn.java.ftp;

import static com.javamaster.b2c.cloud.test.learn.java.utils.PropertiesUtils.getIntProp;
import static com.javamaster.b2c.cloud.test.learn.java.utils.PropertiesUtils.getProp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author yudong
 * @date 2019/12/16
 */
@Slf4j
public class SftpTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Test
    public void test() {
        SftpClient sftpClient = new SftpClient(getProp("SftpServer.TEST_USERNAME"), getProp("SftpServer.TEST_PASSWORD"),
                getProp("SftpServer.SERVER_17"), getIntProp("SftpServer.PORT"));
        try {
            sftpClient.connect();
            List<String> fileNames = sftpClient.getFileNames(getProp("LogPath.CONTROL"), "out.log.2019-12-1");
            sftpClient.disconnect();
            System.out.println(fileNames);
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            sftpClient.disconnect();
        }
    }

    @Test
    public void testPrdControl() throws Exception {
        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(getProp("SftpServer.PRD_USERNAME"), getProp("SftpServer.PRD_PASSWORD"),
                    getProp("SftpServer.SERVER_98"), getIntProp("SftpServer.PORT"));
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(getProp("LogPath.CONTROL"), "G:/logs/control/logs1",
                        "out.log", "out.log.2019-12-1");
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });

        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(getProp("SftpServer.PRD_USERNAME"), getProp("SftpServer.PRD_PASSWORD"),
                    getProp("SftpServer.SERVER_99"), getIntProp("SftpServer.PORT"));
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(getProp("LogPath.CONTROL"), "G:/logs/control/logs2",
                        "out.log", "out.log.2019-12-1");
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    @Test
    public void testPrdMana() throws Exception {
        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(getProp("SftpServer.PRD_USERNAME"), getProp("SftpServer.PRD_PASSWORD"),
                    getProp("SftpServer.SERVER_100"), getIntProp("SftpServer.PORT"));
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(getProp("LogPath.MANA"), "G:/logs/mana/logs1",
                        "out.log", "out.log.2019-12-1");
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });

        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(getProp("SftpServer.PRD_USERNAME"), getProp("SftpServer.PRD_PASSWORD"),
                    getProp("SftpServer.SERVER_101"), getIntProp("SftpServer.PORT"));
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(getProp("LogPath.MANA"), "G:/logs/mana/logs2",
                        "out.log", "out.log.2019-12-1");
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    @Test
    public void testPrdHonor() throws Exception {
        String localPath = "G:/logs/statistical/logs";
        String name = "service-mall-statistical.log";
        String prefix = "service-mall-statistical-info-2019-12-";

        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(getProp("SftpServer.PRD_USERNAME"), getProp("SftpServer.PRD_PASSWORD"),
                    getProp("SftpServer.SERVER_90"), getIntProp("SftpServer.PORT"));
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(getProp("LogPath.MALL_STATISTICAL"), localPath + "1",
                        name, prefix);
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });

        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(getProp("SftpServer.PRD_USERNAME"), getProp("SftpServer.PRD_PASSWORD"),
                    getProp("SftpServer.SERVER_91"), getIntProp("SftpServer.PORT"));
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(getProp("LogPath.MALL_STATISTICAL"), localPath + "2",
                        name, prefix);
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(120, TimeUnit.SECONDS);
    }

}
