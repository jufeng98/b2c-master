package com.javamaster.b2c.cloud.test.learn.java.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.IOException;

/**
 * @author yudong
 * @date 2019/5/9
 */
@Slf4j
public class PrintTest {

    @Test
    @SneakyThrows
    public void test() {
        Book book = new Book();
        Printable printable = (graphics, pageFormat, pageIndex) -> {
            BufferedImage bufferedImage;
            try {
                bufferedImage = ImageIO.read(ResourceUtils.getFile("classpath:安琪拉.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(bufferedImage, 10, 10, (img, infoflags, x, y, width, height) -> false);
            return Printable.PAGE_EXISTS;
        };
        book.append(printable, new PageFormat());
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPageable(book);
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        if (printerJob.printDialog(attributeSet)) {
            printerJob.print(attributeSet);
        }
    }

    @Test
    @SneakyThrows
    public void test1() {
        for (PrintService printService : PrintServiceLookup.lookupPrintServices(null, null)) {
            log.info(printService.getName());
        }
    }
}
