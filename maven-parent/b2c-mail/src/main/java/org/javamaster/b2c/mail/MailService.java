package org.javamaster.b2c.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author yudong
 * @date 2020/8/14
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPlainEmail(String from, String to, String sub, String txt) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(sub);
        mailMessage.setText(txt);
        mailSender.send(mailMessage);
    }

    public void sendInlineMail(String from, String to, String sub, String htmlTxt) throws
            Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(sub);
        helper.setText(htmlTxt, true);
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile("classpath:welcome.png"));
        helper.addInline("welcome", file);
        mailSender.send(mimeMessage);
    }

    public void sendAttachAndInlineEmail(String from, String to, String sub, String htmlTxt) throws
            Exception {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(sub);
        helper.setText(htmlTxt, true);
        File file = ResourceUtils.getFile("classpath:QQ_logo_login.png");
        helper.addAttachment("附件-image.png", file);
        File file1 = ResourceUtils.getFile("classpath:wx_logo_login.png");
        helper.addInline("wx", file1);
        mailSender.send(mailMessage);
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

}
