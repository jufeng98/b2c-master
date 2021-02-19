package com.javamaster.b2c.cloud.test.redis;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class DynamicClass implements Serializable {
    private static final long serialVersionUID = 1699485758518472460L;

    public String debug() {
        String s = StringUtils.trim("");
        return s;
    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[10240];
        String tmpDir = System.getProperty("java.io.tmpdir");
        File file = new File(
                "D:\\b2c_workspacetrunk\\B2C_CLOUD_TEST\\b2c-cloud-test-redis\\target\\b2c-cloud-test-redis-2.0.0-SNAPSHOT.jar");
        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> enumeration = jarFile.entries();
        File delFile = new File(tmpDir, "BOOT-INF");
        if (delFile.exists()) {
            de(delFile.listFiles());
        }
        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) enumeration.nextElement();
            if (!jarEntry.getName().contains("BOOT-INF")) {
                continue;
            }
            if (jarEntry.getName().endsWith("class") || jarEntry.getName().endsWith(".jar")) {
                System.out.println(jarEntry.getName());
                BufferedInputStream inputStream = new BufferedInputStream(jarFile.getInputStream(jarEntry));
                File path = new File(tmpDir, jarEntry.getName().substring(0, jarEntry.getName().lastIndexOf("/")));
                path.mkdirs();
                File file2 = new File(tmpDir, jarEntry.getName());
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2));
                while ((inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes);
                }
                inputStream.close();
                outputStream.close();
            }

        }
        jarFile.close();
    }

    private static void de(File[] fs) {
        for (File f : fs) {
            while (f.isDirectory()) {
                File[] s1 = f.listFiles();
                f.delete();
                de(s1);
            }
            f.delete();
        }
    }
}
