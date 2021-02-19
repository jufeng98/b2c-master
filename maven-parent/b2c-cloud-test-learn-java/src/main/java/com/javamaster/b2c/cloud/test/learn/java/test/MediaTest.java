package com.javamaster.b2c.cloud.test.learn.java.test;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;

/**
 * Created on 2019/4/28.
 *
 * @author yudong
 */
@Slf4j
public class MediaTest {

    @Test
    @SneakyThrows
    public void test() {
        File file = getTmpFile();
        Encoder encoder = new Encoder();
        MultimediaInfo m = encoder.getInfo(file);
        long ls = m.getDuration();
        log.info("duration:{}ms,width:{},height:{}", ls, m.getVideo().getSize().getWidth(), m.getVideo().getSize().getHeight());
        Files.delete(file.toPath());
    }

    @Test
    @SneakyThrows
    public void test1() {
        File file = getTmpFile();
        log.info("start");
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
        ff.start();
        int length = ff.getLengthInFrames();
        log.info("frames num:{}", length);
        Frame frame = ff.grabFrame();
        int width = frame.image.width();
        int height = frame.image.height();
        log.info("width:{},height:{}", width, height);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawImage(frame.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
                0, 0, null);
        File frameFile = new File("target", "frame.jpg");
        ImageIO.write(bi, "jpg", frameFile);
        ff.stop();
        log.info("end");
    }

    @SneakyThrows
    private File getTmpFile() {
        String source = "http://tmallapi.bluemoon.com.cn/img/group1/M00/04/BA/wKjwDl-P64iEPmMvAAAAALI1Izs586.mp4";
        URL url = new URL(source);
        @Cleanup
        InputStream inputStream = url.openConnection().getInputStream();
        File file = Files.createTempFile(RandomStringUtils.randomAlphabetic(6), ".mp4").toFile();
        @Cleanup
        OutputStream outputStream = new FileOutputStream(file);
        StreamUtils.copy(inputStream, outputStream);
        inputStream.close();
        outputStream.close();
        return file;
    }

}
