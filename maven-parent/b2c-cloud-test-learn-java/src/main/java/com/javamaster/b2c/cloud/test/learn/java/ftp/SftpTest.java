package com.javamaster.b2c.cloud.test.learn.java.ftp;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @date 2019/12/16
 */
@Slf4j
public class SftpTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Test
    public void test() {
        SftpClient sftpClient = new SftpClient(B2cMasterConsts.SftpServer.TEST_USERNAME, B2cMasterConsts.SftpServer.TEST_PASSWORD,
                B2cMasterConsts.SftpServer.SERVER_17, B2cMasterConsts.SftpServer.PORT);
        try {
            sftpClient.connect();
            List<String> fileNames = sftpClient.getFileNames(B2cMasterConsts.LogPath.CONTROL, "out.log.2019-12-1");
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
            SftpClient sftpClient = new SftpClient(B2cMasterConsts.SftpServer.PRD_USERNAME, B2cMasterConsts.SftpServer.PRD_PASSWORD,
                    B2cMasterConsts.SftpServer.SERVER_98, B2cMasterConsts.SftpServer.PORT);
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(B2cMasterConsts.LogPath.CONTROL, "G:/logs/control/logs1",
                        "out.log", "out.log.2019-12-1");
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });

        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(B2cMasterConsts.SftpServer.PRD_USERNAME, B2cMasterConsts.SftpServer.PRD_PASSWORD,
                    B2cMasterConsts.SftpServer.SERVER_99, B2cMasterConsts.SftpServer.PORT);
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(B2cMasterConsts.LogPath.CONTROL, "G:/logs/control/logs2",
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
            SftpClient sftpClient = new SftpClient(B2cMasterConsts.SftpServer.PRD_USERNAME, B2cMasterConsts.SftpServer.PRD_PASSWORD,
                    B2cMasterConsts.SftpServer.SERVER_100, B2cMasterConsts.SftpServer.PORT);
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(B2cMasterConsts.LogPath.MANA, "G:/logs/mana/logs1",
                        "out.log", "out.log.2019-12-1");
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });

        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(B2cMasterConsts.SftpServer.PRD_USERNAME, B2cMasterConsts.SftpServer.PRD_PASSWORD,
                    B2cMasterConsts.SftpServer.SERVER_101, B2cMasterConsts.SftpServer.PORT);
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(B2cMasterConsts.LogPath.MANA, "G:/logs/mana/logs2",
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
            SftpClient sftpClient = new SftpClient(B2cMasterConsts.SftpServer.PRD_USERNAME, B2cMasterConsts.SftpServer.PRD_PASSWORD,
                    B2cMasterConsts.SftpServer.SERVER_90, B2cMasterConsts.SftpServer.PORT);
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(B2cMasterConsts.LogPath.MALL_STATISTICAL, localPath + "1",
                        name, prefix);
            } catch (Exception e) {
                log.error("error", e);
            } finally {
                sftpClient.disconnect();
            }
        });

        executorService.submit(() -> {
            SftpClient sftpClient = new SftpClient(B2cMasterConsts.SftpServer.PRD_USERNAME, B2cMasterConsts.SftpServer.PRD_PASSWORD,
                    B2cMasterConsts.SftpServer.SERVER_91, B2cMasterConsts.SftpServer.PORT);
            try {
                sftpClient.connect();
                sftpClient.downloadLogFiles(B2cMasterConsts.LogPath.MALL_STATISTICAL, localPath + "2",
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
