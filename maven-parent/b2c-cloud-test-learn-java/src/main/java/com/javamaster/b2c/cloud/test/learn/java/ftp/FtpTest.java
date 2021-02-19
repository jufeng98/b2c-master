package com.javamaster.b2c.cloud.test.learn.java.ftp;

import com.javamaster.b2c.cloud.test.learn.java.utils.FtpUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author yudong
 * @date 2019/5/31
 */
public class FtpTest {

    public static void main(String[] args) {
        FtpUtils.newFtpServer();
    }
    private static DefaultKaptcha defaultKaptcha;

    @BeforeClass
    @SneakyThrows
    public static void createFtpServer() {
        FtpUtils.newFtpServer();
        defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.textproducer.font.size", "45");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
    }

    @Test
    @SneakyThrows
    public void upload() {
        System.out.println(new File("").getAbsolutePath());
        System.out.println(Paths.get("").toAbsolutePath());
        BufferedImage bufferedImage = defaultKaptcha.createImage(defaultKaptcha.createText());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", outputStream);
        outputStream.flush();
        FtpUtils.uploadFile("127.0.0.1", 21, "root", "root", "/", "Kaptcha验证码.jpg", outputStream.toByteArray());
        outputStream.close();
    }
}
