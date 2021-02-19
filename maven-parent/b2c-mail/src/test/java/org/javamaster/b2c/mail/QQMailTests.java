package org.javamaster.b2c.mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpringBootTest
public class QQMailTests {

    private static JavaMailSenderImpl mailSender;
    private static String username;

    static {
        try {
            File file = new File("D:\\User\\qq邮箱账号.txt");
            String[] strings = StreamUtils.copyToString(new FileInputStream(file), StandardCharsets.UTF_8).split("\\|");
            username = strings[0];
            String password = strings[1];
            mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.qq.com");
            mailSender.setUsername(username);
            mailSender.setPassword(password);
            mailSender.setDefaultEncoding("UTF-8");
            Properties prop = new Properties();
            prop.put("mail.debug", true);
            prop.put("mail.smtp.starttls.required", false);
            mailSender.setJavaMailProperties(prop);
        } catch (IOException ignored) {
        }
    }

    @Autowired
    private MailService mailService;

    @BeforeEach
    public void init() {
        mailService.setMailSender(mailSender);
    }

    @Test
    public void sendPlainEmail() {
        mailService.sendPlainEmail(username, "375709770@qq.com", "测试邮件", "这是测试邮件的内容");
    }

    @Test
    public void sendInlineMail() throws Exception {
        mailService.sendInlineMail(username, "375709770@qq.com",
                "测试邮件", "<html><body><img src=\"cid:welcome\">这是测试邮件的内容</body></html>");
    }

    @Test
    public void sendAttachAndInlineEmail() throws Exception {
        mailService.sendAttachAndInlineEmail(username, "375709770@qq.com",
                "测试邮件", "<html><body><img src=\"cid:wx\">这是测试邮件的内容</body></html>");
    }


}
