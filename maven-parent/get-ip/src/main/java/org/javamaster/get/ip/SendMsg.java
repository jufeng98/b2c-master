package org.javamaster.get.ip;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import org.apache.log4j.lf5.util.StreamUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * @author yudong
 * @date 2022/6/6
 */
public class SendMsg {
    private static final Logger logger = Logger.getLogger(SendMsg.class.getName());

    public static void start() {
        CronUtil.schedule("0 17 * * 1,2,3,4,5", (Task) () -> {
            String content = "{\"msgtype\":\"text\",\"text\":{\"content\":\"老铁们，是不是要写日志了呢？\",\"mentioned_list\":[\"@all\"]}}";
            try {
                URLConnection connection = new URL("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=2d756d0b-7ec5-4356-a43b-60b40cbf4995").openConnection();
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(content.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                logger.info(new String(StreamUtils.getBytes(inputStream), StandardCharsets.UTF_8));
                inputStream.close();
            } catch (Exception e) {
                logger.severe(e.getClass() + " " + e.getMessage());
            }
        });
        CronUtil.start();
    }

}
