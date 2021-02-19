package com.javamaster.b2c.cloud.test.learn.java.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class SocketTest {

    @Test
    public void test() throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8520), 6000);
        try (InputStream inputStream = socket.getInputStream()) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                log.info(scanner.nextLine());
            }
        }
    }

    @Test
    public void test1() throws Exception {
        InetAddress[] inetAddresses = InetAddress.getAllByName("www.baidu.com");
        for (InetAddress inetAddress : inetAddresses) {
            System.out.println(inetAddress.toString());
        }
        InetAddress local = InetAddress.getLocalHost();
        log.info(local.toString());
    }

    @Test
    public void test2() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);
        System.out.println("开始监听请求:");
        while (true) {
            Socket socket = serverSocket.accept();
            handlerRequest(socket);
        }
    }

    @SneakyThrows
    private void handlerRequest(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                if ("".equals(line)) {
                    String body = scanner.nextLine();
                    System.out.println(body);
                    break;
                }
            }
            outputStream.write("welcome to server socket!".getBytes());
        } finally {
            socket.close();
        }
    }


    @Test
    public void test3() throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
        while (true) {
            Socket socket = socketChannel.socket();
            handlerRequest(socket);
        }
    }

    @Test
    public void test4() throws Exception {
        URL url = new URL("http://127.0.0.1:8761/");
        URLConnection connection = url.openConnection();
        String authorize = "user:password";
        connection.setRequestProperty("Authorization", "Basic " + Base64Utils.encodeToString(authorize.getBytes()));
        connection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }

    @SneakyThrows
    public static void main(String[] args) {
        JEditorPane jEditorPane = new JEditorPane();
        jEditorPane.setPage(new URL("http://127.0.0.1:8761/"));
        jEditorPane.setEditable(false);

        JFrame jFrame = new JFrame();
        jFrame.add(jEditorPane);
        jFrame.setSize(1500, 800);
        jFrame.setVisible(true);
    }
}
