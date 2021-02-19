package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.utils.*;
import com.javamaster.b2c.cloud.test.learn.java.utils.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.util.Pair;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * Created on 2019/1/25.<br/>
 *
 * @author yudong
 */
@Slf4j
public class SecurityTest {

    @BeforeClass
    public static void init() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Test
    @SneakyThrows
    public void sha() {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update("hello world".getBytes(StandardCharsets.UTF_8));
        // 计算SHA指纹,20字节(160位)
        byte[] bytes = messageDigest.digest();
        String fingerPrint = HexUtils.toHexString(bytes).toUpperCase();
        log.info("{}", fingerPrint);
        fingerPrint = MD5Utils.byteArrayToHexString(bytes);
        log.info("{}", fingerPrint);
    }

    @Test
    @SneakyThrows
    public void md5() {
        log.info("{}", MD5Utils.encode("hello world"));
        String s1 = "Er78s1hcT4Tyoaj2" +
                "wx" +
                "123" +
                "json" +
                "1573535094332" +
                "2.5.0" +
                "{}" +
                "Er78s1hcT4Tyoaj2";
        String s = MD5Utils.encode(s1);
        log.info(s);
    }

    @Test
    public void testAes() {
        String password = "qq123123";
        String encryptHexMsg = AesUtils.aesEncrypt("这是最高机密的信息!!!", password);
        log.info("AES加密后的16进制信息:{}", encryptHexMsg);

        String plainMsg = AesUtils.aesDecrypt(encryptHexMsg, password);
        log.info("AES解密后的原始信息:{}", plainMsg);

        SecretKey secretKey = AesUtils.getRandomAesKey();
        encryptHexMsg = AesUtils.aesEncrypt("这是最高机密的信息!!!", secretKey);
        log.info("AES加密后的16进制信息:{}", encryptHexMsg);

        plainMsg = AesUtils.aesDecrypt(encryptHexMsg, secretKey);
        log.info("AES解密后的原始信息:{}", plainMsg);
    }

    @Test
    @SneakyThrows
    public void testAes1() {
        String password = "qq123123";
        File file = ResourceUtils.getFile("classpath:car.json");
        byte[] originalBytes = StreamUtils.copyToByteArray(new FileInputStream(file));
        log.info("original:{}", new String(originalBytes));

        byte[] encryptBytes = AesUtils.encryptFlow(password, originalBytes);
        log.info("encrypt:{}", new String(encryptBytes));

        originalBytes = AesUtils.decryptFlow(password, encryptBytes);
        log.info("original:{}", new String(originalBytes));
    }

    @Test
    @SneakyThrows
    public void testRsa() {
        // 模拟https的工作原理
        SecretKey secretKey = AesUtils.getRandomAesKey();
        KeyPair keyPair = RsaUtils.getRasKey();
        Pair<String, String> pair = RsaUtils.rsaAesEncrypt("这是最高机密的信息!!!", secretKey, keyPair.getPublic());
        log.info("RSA加密后的AES对称密钥:{}\r\nAES加密后的信息:{}", pair.getFirst(), pair.getSecond());

        String originalMsg = RsaUtils.rsaAesDecrypt(pair.getFirst(), pair.getSecond(), keyPair.getPrivate());
        log.info("解密后的信息:{}", originalMsg);
    }
}
