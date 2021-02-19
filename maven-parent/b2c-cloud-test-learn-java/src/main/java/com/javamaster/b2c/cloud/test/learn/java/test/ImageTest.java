package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.thinking.RasterImageFrame;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author yudong
 * @date 2020/9/30
 */
@Slf4j
public class ImageTest {

    @Test
    @SneakyThrows
    public void test() {
        BufferedImage bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
        log.info("{},{}", bufferedImage.getWidth(), bufferedImage.getHeight());
        Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReadersByFormatName("JPEG");
        if (imageReaderIterator.hasNext()) {
            ImageReader imageReader = imageReaderIterator.next();
            log.info(imageReader.toString());
            log.info(imageReader.getOriginatingProvider().getVendorName());
        }
        for (String writerFileSuffix : ImageIO.getWriterFileSuffixes()) {
            log.info(writerFileSuffix);
        }
    }

    @Test
    @SneakyThrows
    public void test1() {
        BufferedImage bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
        int[] argb = new int[4];
        bufferedImage.getRaster().getPixel(30, 30, argb);
        log.info(Arrays.toString(argb));
        Color color = new Color(argb[0], argb[1], argb[2], argb[3]);
        log.info(color.toString());
        ColorModel colorModel = bufferedImage.getColorModel();
        log.info(colorModel.toString());
        log.info(bufferedImage.getRaster().getDataElements(30, 30, null).toString());
    }

    @Test
    @SneakyThrows
    public void rotateImage() {
        BufferedImage bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(180), bufferedImage.getWidth() / 2.0, bufferedImage.getHeight() / 2.0);
        BufferedImageOp op = new AffineTransformOp(transform, 1);
        BufferedImage bufferedImage1 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        op.filter(bufferedImage, bufferedImage1);
        ImageIO.write(bufferedImage1, "JPG", new File("image.jpg"));
    }

    @Test
    @SneakyThrows
    public void rescaleImage() {
        BufferedImage bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
        // scaleFactor >1 ,会使图形变亮
        RescaleOp op = new RescaleOp(1.0f, 20.0f, null);
        BufferedImage bufferedImage1 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        op.filter(bufferedImage, bufferedImage1);
        ImageIO.write(bufferedImage1, "JPG", new File("image.jpg"));
    }

    @Test
    @SneakyThrows
    public void lookup() {
        BufferedImage bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
        short[] negative = new short[256];
        for (int i = 0; i < 256; i++) {
            negative[i] = (short) (255 - i);
        }
        ShortLookupTable shortLookupTable = new ShortLookupTable(0, negative);
        LookupOp op = new LookupOp(shortLookupTable, null);
        BufferedImage bufferedImage1 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        op.filter(bufferedImage, bufferedImage1);
        ImageIO.write(bufferedImage1, "JPG", new File("image.jpg"));
    }

    @Test
    @SneakyThrows
    public void convolve() {
        BufferedImage bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
        float[] elements = {
                0f, -1f, 0f,
                -1f, 4f, -1f,
                0f, -1f, 0f,
        };
        Kernel kernel = new Kernel(3, 3, elements);
        ConvolveOp op = new ConvolveOp(kernel);
        BufferedImage bufferedImage1 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        op.filter(bufferedImage, bufferedImage1);
        ImageIO.write(bufferedImage1, "JPG", new File("image.jpg"));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new RasterImageFrame();
            frame.setTitle("RasterImageTest");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}
