package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.thinking.ImageTransferable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;

/**
 * @author yudong
 * @date 2020/9/30
 */
@Slf4j
public class ClipboardTest {

    @Test
    @SneakyThrows
    public void test() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection("hello world from java");
        clipboard.setContents(stringSelection, null);
    }

    @Test
    @SneakyThrows
    public void test1() {
        DataFlavor dataFlavor = DataFlavor.stringFlavor;
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (clipboard.isDataFlavorAvailable(dataFlavor)) {
            String data = (String) clipboard.getData(dataFlavor);
            log.info(data);
        }
    }

    @Test
    @SneakyThrows
    public void test2() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        for (DataFlavor availableDataFlavor : clipboard.getAvailableDataFlavors()) {
            log.info(availableDataFlavor.toString());
        }
    }

    @Test
    @SneakyThrows
    public void test3() {
        BufferedImage bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
        ImageTransferable imageTransferable = new ImageTransferable(bufferedImage);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(imageTransferable, null);
    }

    @Test
    @SneakyThrows
    public void test4() {
        Desktop.getDesktop().browse(new URI("http://www.baidu.com"));
    }

    @Test
    @SneakyThrows
    public void test5() {
        Desktop.getDesktop().open(new File("D:/"));
    }

}
