package com.javamaster.b2c.cloud.test.learn.java.utils;

import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created on 2019/1/25.<br/>
 *
 * @author yudong
 */
public class AesUtils {

    /**
     * 必须是8的倍数
     */
    private static final int PASSWORD_LENGTH = 16;
    private static final String AES = "AES/CBC/PKCS5Padding";

    /**
     * @param originalMsg 待加密的信息
     * @param password    密码
     */
    @SneakyThrows
    public static String aesEncrypt(String originalMsg, String password) {
        SecretKey secretKey = getFixAesKey(password);
        return aesEncrypt(originalMsg, secretKey);
    }

    @SneakyThrows
    public static String aesEncrypt(String originalMsg, SecretKey secretKey) {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[PASSWORD_LENGTH]));
        byte[] encryptedMsgBytes = cipher.doFinal(originalMsg.getBytes(StandardCharsets.UTF_8));
        return HexUtils.toHexString(encryptedMsgBytes).toUpperCase();
    }

    @SneakyThrows
    public static byte[] aesEncrypt1(String originalMsg, String password) {
        SecretKey secretKey = new SecretKeySpec(Arrays.copyOf(DigestUtils.sha1(password), 16), "AES");
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[PASSWORD_LENGTH]));
        return cipher.doFinal(originalMsg.getBytes(StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public static String aesDecrypt(String hexEncryptMsg, String password) {
        SecretKey secretKey = getFixAesKey(password);
        return aesDecrypt(hexEncryptMsg, secretKey);
    }

    @SneakyThrows
    public static String aesDecrypt(String hexEncryptHexMsg, SecretKey secretKey) {
        byte[] encryptedMsgBytes = HexUtils.fromHexString(hexEncryptHexMsg.toLowerCase());
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[PASSWORD_LENGTH]));
        byte[] originalMsg = cipher.doFinal(encryptedMsgBytes);
        return new String(originalMsg, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static SecretKey getFixAesKey(String password) {
        Assert.isTrue(password.length() <= 16, "密码长度不大于16位");
        int size = PASSWORD_LENGTH - password.length();
        password = password + StringUtils.repeat(" ", size);
        byte[] keyData = password.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyData, "AES");
    }

    @SneakyThrows
    public static SecretKey getRandomAesKey() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(secureRandom);
        return keyGenerator.generateKey();
    }

    @SneakyThrows
    public static byte[] encryptFlow(String password, byte[] fileBytes) {
        Cipher cipher = Cipher.getInstance(AES);
        SecretKey secretKey = getFixAesKey(password);
        byte[] bytes = new byte[PASSWORD_LENGTH];
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(bytes));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher);
        cipherOutputStream.write(fileBytes);
        cipherOutputStream.flush();
        cipherOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    @SneakyThrows
    public static byte[] decryptFlow(String password, byte[] encryptBytes) {
        Cipher cipher = Cipher.getInstance(AES);
        SecretKey secretKey = getFixAesKey(password);
        byte[] bytes = new byte[PASSWORD_LENGTH];
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(bytes));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encryptBytes);
        CipherInputStream cipherInputStream = new CipherInputStream(byteArrayInputStream, cipher);
        byte[] originalBytes = StreamUtils.copyToByteArray(cipherInputStream);
        cipherInputStream.close();
        return originalBytes;
    }

    @SneakyThrows
    public static String decrypt(String sSrc, String sKey) {
        StringBuilder sKeyBuilder = new StringBuilder(sKey);
        while (sKeyBuilder.length() < 16) {
            sKeyBuilder.append(":BlZEmoon");
        }
        sKey = sKeyBuilder.toString();
        sKey = sKey.substring(0, 16);
        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, skeySpec);
        byte[] encrypted1 = (new BASE64Decoder()).decodeBuffer(sSrc);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static String encrypt(String sSrc, String sKey) {
        StringBuilder sKeyBuilder = new StringBuilder(sKey);
        while (sKeyBuilder.length() < 16) {
            sKeyBuilder.append(":BlZEmoon");
        }
        sKey = sKeyBuilder.toString();

        sKey = sKey.substring(0, 16);
        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
        return (new BASE64Encoder()).encode(encrypted);
    }
}
