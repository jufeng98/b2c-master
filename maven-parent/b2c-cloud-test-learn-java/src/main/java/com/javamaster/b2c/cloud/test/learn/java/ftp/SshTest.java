package com.javamaster.b2c.cloud.test.learn.java.ftp;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class SshTest {

    public static void main(String[] args) {
        SftpClient sftpClient = new SftpClient("root", "root", "127.0.0.1", 22);
        try {
            sftpClient.connect();
            while (true) {
                Scanner in = new Scanner(System.in);
                System.out.print("input command:");
                String command = in.nextLine();
                if ("exit".equals(command)) {
                    break;
                }
                System.out.println(sftpClient.shell(command));
            }
        } finally {
            sftpClient.disconnect();
        }
    }

}
