package com.javamaster.b2c.cloud.test.learn.java.utils;

import com.eos.system.utility.Assert;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.util.StreamUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

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
}
