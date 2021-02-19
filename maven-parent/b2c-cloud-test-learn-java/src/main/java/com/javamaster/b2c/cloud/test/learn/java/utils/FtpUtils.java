package com.javamaster.b2c.cloud.test.learn.java.utils;

import lombok.SneakyThrows;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2019/5/31
 */
public class FtpUtils {

    @SneakyThrows
    public static void newFtpServer() {
        FtpServerFactory serverFactory = new FtpServerFactory();
        BaseUser user = new BaseUser();
        user.setName("anonymous");
        user.setHomeDirectory("E:\\软件");
        serverFactory.getUserManager().save(user);

        BaseUser superUser = new BaseUser();
        superUser.setName("root");
        superUser.setPassword("root");
        superUser.setHomeDirectory("E:\\软件");
        List<Authority> authority = new ArrayList<>();
        authority.add(new WritePermission());
        user.setAuthorities(authority);
        serverFactory.getUserManager().save(superUser);

        FtpServer server = serverFactory.createServer();
        server.start();
        System.out.println("ftp服务器启动完成...");
    }


    private static FTPClient ftpClient = new FTPClient();

    @SneakyThrows
    public static void uploadFile(String url, int port, String username,
                                  String password, String path, String filename, byte[] fileBytes) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes)) {
            ftpClient.connect(url, port);
            ftpClient.login(username, password);
            ftpClient.setControlEncoding(StandardCharsets.UTF_8.name());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new RuntimeException("connect failed reply code:" + reply);
            }
            boolean change = ftpClient.changeWorkingDirectory(path);
            if (!change) {
                throw new RuntimeException("change directory failed:" + path);
            }
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            boolean result = ftpClient.storeFile(filename, inputStream);
            if (!result) {
                throw new RuntimeException("upload file failed:" + filename);
            }
        } finally {
            // ftpClient.logout();
            // ftpClient.disconnect();
        }
    }

    @SneakyThrows
    public static byte[] downloadFile(String url, int port, String username,
                                      String password, String remotePath, String fileName) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ftpClient.setControlEncoding(StandardCharsets.UTF_8.name());
            ftpClient.connect(url, port);
            ftpClient.login(username, password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new RuntimeException("connect failed reply code:" + reply);
            }
            ftpClient.changeWorkingDirectory(remotePath);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            List<FTPFile> ftpFileList = Arrays.stream(ftpFiles)
                    .filter(ftpFile -> {
                        if (ftpFile.getName().equals(fileName)) {
                            return true;
                        } else {
                            return false;
                        }
                    })
                    .collect(Collectors.toList());
            if (ftpFileList.isEmpty()) {
                throw new RuntimeException("can not found the file in ftp server:" + fileName);
            }
            ftpClient.retrieveFile(ftpFileList.get(0).getName(), outputStream);
            outputStream.flush();
            byte[] fileBytes = outputStream.toByteArray();
            return fileBytes;
        } finally {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

}
