package com.javamaster.b2c.cloud.test.learn.java.utils;

import lombok.SneakyThrows;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.data.util.Pair;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.*;

/**
 * @author yudong
 * @date 2020/10/4
 */
public class RsaUtils {

    public static final int KEY_SIZE = 512;


    @SneakyThrows
    public static Pair<String, String> rsaAesEncrypt(String originalMsg, SecretKey secretKey, PublicKey publicKey) {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.WRAP_MODE, publicKey);
        byte[] secretKeyBytes = cipher.wrap(secretKey);
        String hexSecretKeyStr = HexUtils.toHexString(secretKeyBytes).toUpperCase();
        String hexEncryptMsg = AesUtils.aesEncrypt(originalMsg, secretKey);
        return Pair.of(hexSecretKeyStr, hexEncryptMsg);
    }


    @SneakyThrows
    public static String rsaAesDecrypt(String hexEncryptedSecretKey, String hexEncryptMsg, PrivateKey privateKey) {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.UNWRAP_MODE, privateKey);
        byte[] encryptedSecretKey = HexUtils.fromHexString(hexEncryptedSecretKey.toLowerCase());
        SecretKey secretKey = (SecretKey) cipher.unwrap(encryptedSecretKey, "AES", Cipher.SECRET_KEY);
        return AesUtils.aesDecrypt(hexEncryptMsg, secretKey);
    }

    @SneakyThrows
    public static KeyPair getRasKey() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(KEY_SIZE, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }
}
