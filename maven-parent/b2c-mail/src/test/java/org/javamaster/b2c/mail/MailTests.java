package org.javamaster.b2c.mail;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@SpringBootTest
public class MailTests {

    private static GreenMail greenMail;
    @Autowired
    private MailService mailService;

    @BeforeAll
    public static void startMailService() {
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("root@javamaster.com", "root");
        greenMail.start();
    }

    @AfterAll
    public static void stopMailService() {
        greenMail.stop();
    }

    @Test
    public void sendPlainEmail() throws Exception {
        mailService.sendPlainEmail("root@javamaster.com", "375709770@qq.com", "测试邮件", "这是测试邮件的内容");

        greenMail.waitForIncomingEmail(2000, 1);
        MimeMessage[] mimeMessages = greenMail.getReceivedMessages();
        System.out.println(mimeMessages[0].getSubject());
        System.out.println(mimeMessages[0].getContent());
    }

    @Test
    public void sendInlineMail() throws Exception {
        mailService.sendInlineMail("root@javamaster.com", "375709770@qq.com",
                "测试邮件", "<html><body><img src=\"cid:welcome\">这是测试邮件的内容</body></html>");

        greenMail.waitForIncomingEmail(2000, 1);
        MimeMessage[] mimeMessages = greenMail.getReceivedMessages();
        System.out.println(mimeMessages[0].getSubject());
        MimeMultipart mimeMultipart = (MimeMultipart) mimeMessages[0].getContent();
        System.out.println(mimeMultipart.getContentType());
    }

    @Test
    public void sendAttachAndInlineEmail() throws Exception {
        mailService.sendAttachAndInlineEmail("root@javamaster.com", "375709770@qq.com",
                "测试邮件", "<html><body><img src=\"cid:wx\">这是测试邮件的内容</body></html>");

        greenMail.waitForIncomingEmail(2000, 1);
        MimeMessage[] mimeMessages = greenMail.getReceivedMessages();
        System.out.println(mimeMessages[0].getSubject());
        MimeMultipart mimeMultipart = (MimeMultipart) mimeMessages[0].getContent();
        System.out.println(mimeMultipart.getContentType());
    }


}
