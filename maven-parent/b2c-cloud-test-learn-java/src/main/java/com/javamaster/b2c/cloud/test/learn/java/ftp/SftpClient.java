package com.javamaster.b2c.cloud.test.learn.java.ftp;

import com.jcraft.jsch.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2019/12/16
 */
@Slf4j
public class SftpClient {

    private ChannelSftp channelSftp;

    private ChannelShell channelShell;

    private ChannelExec channelExec;

    private Session session;
    /**
     * SFTP 登录用户名
     */
    private final String username;
    /**
     * SFTP 登录密码
     */
    private String password;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * SFTP 服务器地址IP地址
     */
    private final String host;
    /**
     * SFTP 端口
     */
    private final int port;

    private final int TIMEOUT = 6000;

    /**
     * 构造基于密码认证的sftp对象
     */
    SftpClient(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SftpClient(String username, String host, int port, String privateKey) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.privateKey = privateKey;
    }

    /**
     * 连接sftp服务器
     */
    @SneakyThrows
    void connect() {
        JSch jsch = new JSch();
        if (privateKey != null) {
            // 设置私钥
            jsch.addIdentity(privateKey);
        }
        session = jsch.getSession(username, host, port);
        if (password != null) {
            session.setPassword(password);
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
    }

    @SneakyThrows
    public String exec(String command) {
        checkExecChannelConnect();
        channelExec.setCommand(command);
        channelExec.connect(TIMEOUT);
        return readResult(channelExec.getInputStream());
    }

    @SneakyThrows
    public String shell(String command) {
        checkShellChannelConnect();
        command = command + "\n";
        InputStream in = channelShell.getInputStream();
        OutputStream out = channelShell.getOutputStream();
        out.write(command.getBytes(StandardCharsets.UTF_8));
        out.flush();
        return readResult(in);
    }

    @SneakyThrows
    private String readResult(InputStream in) {
        StringBuilder sb = new StringBuilder();
        int beat = 0;
        while (beat <= 3) {
            if (in.available() > 0) {
                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                sb.append(new String(bytes));
                beat = 0;
            } else {
                if (sb.length() > 0) {
                    ++beat;
                }
                TimeUnit.SECONDS.sleep(1);
            }
        }
        return sb.toString();
    }

    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param basePath     服务器的基础路径
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     */
    @SneakyThrows
    public void upload(String basePath, String directory, String sftpFileName, InputStream input) {
        checkSftpChannelConnect();
        try {
            channelSftp.cd(basePath);
            channelSftp.cd(directory);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String[] dirs = directory.split("/");
            String tempPath = basePath;
            for (String dir : dirs) {
                if (null == dir || "".equals(dir)) {
                    continue;
                }
                tempPath += "/" + dir;
                try {
                    channelSftp.cd(tempPath);
                } catch (SftpException ex) {
                    channelSftp.mkdir(tempPath);
                    channelSftp.cd(tempPath);
                }
            }
        }
        channelSftp.put(input, sftpFileName);
    }


    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的完整路径
     */
    @SneakyThrows
    void download(String directory, String downloadFile, String saveFile) {
        checkSftpChannelConnect();
        Assert.hasText(directory, "directory can't be blank");
        channelSftp.cd(directory);
        channelSftp.get(downloadFile, new FileOutputStream(saveFile));
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    @SneakyThrows
    public byte[] download(String directory, String downloadFile) {
        checkSftpChannelConnect();
        Assert.hasText(directory, "directory can't be blank");
        channelSftp.cd(directory);
        InputStream is = channelSftp.get(downloadFile);
        return IOUtils.toByteArray(is);
    }

    @SneakyThrows
    public void downloadLogFiles(String logPath, String savePath, String currentFileName, String historyFileNamePrefix) {
        checkSftpChannelConnect();
        File path = new File(savePath);
        if (!path.exists()) {
            boolean success = path.mkdirs();
            if (!success) {
                throw new FileSystemException("can't create path " + password);
            }
        }
        String saveFile = savePath + "/" + currentFileName;
        download(logPath, currentFileName, saveFile);
        log.info("download {} to {} finished", currentFileName, saveFile);
        List<String> fileNames = getFileNames(logPath, historyFileNamePrefix);
        for (String fileName : fileNames) {
            saveFile = savePath + "/" + fileName;
            download(logPath, fileName, saveFile);
            log.info("download {} to {} finished%n", fileName, saveFile);
        }
    }

    List<String> getFileNames(String directory, String prefix) {
        checkSftpChannelConnect();
        return listFiles(directory).stream()
                .filter(lsEntry -> lsEntry.getFilename().startsWith(prefix))
                .map(ChannelSftp.LsEntry::getFilename)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    @SneakyThrows
    public void delete(String directory, String deleteFile) {
        checkSftpChannelConnect();
        channelSftp.cd(directory);
        channelSftp.rm(deleteFile);
    }


    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     */
    @SneakyThrows
    @SuppressWarnings("ALL")
    Vector<ChannelSftp.LsEntry> listFiles(String directory) {
        checkSftpChannelConnect();
        return channelSftp.ls(directory);
    }

    @SneakyThrows
    public void checkSftpChannelConnect() {
        if (channelSftp != null) {
            return;
        }
        channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect(TIMEOUT);
    }

    @SneakyThrows
    public void checkShellChannelConnect() {
        if (channelShell != null) {
            return;
        }
        channelShell = (ChannelShell) session.openChannel("shell");
        channelShell.connect(TIMEOUT);
    }

    @SneakyThrows
    public void checkExecChannelConnect() {
        if (channelExec != null) {
            return;
        }
        channelExec = (ChannelExec) session.openChannel("exec");
        channelExec.setErrStream(System.err);
    }

    /**
     * 关闭连接
     */
    void disconnect() {
        if (channelSftp != null) {
            if (channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
        }
        if (channelShell != null) {
            if (channelShell.isConnected()) {
                channelShell.disconnect();
            }
        }
        if (channelExec != null) {
            if (channelExec.isConnected()) {
                channelExec.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

}
