package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.utils.FileUtils;
import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * Created on 2018/12/29.<br/>
 *
 * @author yudong
 */
public class Base64Test {

    @Test
    public void test() throws Exception {

        String base64OfPictureStr = FileUtils.readAsString(ResourceUtils.getFile("classpath:base64-picture.txt"));

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(base64OfPictureStr);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        System.out.println(Arrays.toString(b));

        System.out.println(Arrays.toString(Base64Utils.decodeFromString(base64OfPictureStr)));

        File file1 = new File("F://a.png");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(b);
        fileOutputStream.close();
    }
}
