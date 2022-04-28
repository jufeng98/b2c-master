package org.javamaster.get.ip;

import net.sourceforge.tess4j.Tesseract;
import static org.javamaster.get.ip.Application.showTray;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2022/4/28
 */
public class ClipboardListener implements ClipboardOwner {
    private static final Logger logger = Logger.getLogger(Application.class.getName());
    public static Tesseract instance;

    public static void startListener() {
        instance = new Tesseract();
        instance.setDatapath("D:\\Program Files\\Tesseract-OCR\\tessdata");
        instance.setLanguage("chi_sim");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(clipboard.getContents(null), new ClipboardListener());
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        try {
            Thread.sleep(1000);
            if (clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)) {
                BufferedImage image = (BufferedImage) clipboard.getData(DataFlavor.imageFlavor);
                String text = instance.doOCR(image);

                logger.info(text);
                showTray(text);

                Path path = Paths.get("C:\\Users\\yu\\Documents\\clipboard.txt");
                byte[] bytes = Files.readAllBytes(path);
                String s = new String(bytes, StandardCharsets.UTF_8);
                s += "\r\n" + text;
                Files.write(path, s.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
            }
            // 不影响剪切板内容
            // 每次剪切板变动，剪切板的所有者会被剥夺，所以要重新设置自己为所有者，才能监听下一次剪切板变动
            clipboard.setContents(clipboard.getContents(null), this);
        } catch (Exception e) {
            logger.severe(e.getClass() + " " + e.getMessage());
        }
    }

}
