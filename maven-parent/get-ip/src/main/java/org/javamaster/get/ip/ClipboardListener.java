package org.javamaster.get.ip;

import net.sourceforge.tess4j.Tesseract;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2022/4/28
 */
public class ClipboardListener implements ClipboardOwner {
    private static final Logger logger = Logger.getLogger(ClipboardListener.class.getName());
    private static final Tesseract INSTANCE;
    private static final ClipboardListener CLIPBOARD_LISTENER;

    static {
        INSTANCE = new Tesseract();
        INSTANCE.setDatapath("D:\\Program Files\\Tesseract-OCR\\tessdata");
        INSTANCE.setLanguage("chi_sim");
        CLIPBOARD_LISTENER = new ClipboardListener();
    }

    public static void startListener() {
        Executors.newSingleThreadExecutor().execute(() -> {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(clipboard.getContents(null), CLIPBOARD_LISTENER);
        });
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        try {
            Thread.sleep(1000);
            if (clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)) {
                BufferedImage image = (BufferedImage) clipboard.getData(DataFlavor.imageFlavor);
                String text = INSTANCE.doOCR(image);
                logger.info(text);
            }
            // 不影响剪切板内容
            // 每次剪切板变动，剪切板的所有者会被剥夺，所以要重新设置自己为所有者，才能监听下一次剪切板变动
            clipboard.setContents(clipboard.getContents(null), CLIPBOARD_LISTENER);
        } catch (Exception e) {
            logger.severe(e.getClass() + " " + e.getMessage());
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException ignored) {
            }
            startListener();
        }
    }

}
