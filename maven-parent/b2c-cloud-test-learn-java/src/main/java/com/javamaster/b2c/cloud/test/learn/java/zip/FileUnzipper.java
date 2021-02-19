package com.javamaster.b2c.cloud.test.learn.java.zip;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author yudong
 * @date 2019/7/10
 */
public class FileUnzipper {

    private final String zipFileDir;
    private final String zipFileName;
    private final String unzipDir;

    public FileUnzipper(String zipFileDir, String zipFileName, String unzipDir) {
        this.zipFileDir = zipFileDir;
        this.zipFileName = zipFileName;
        this.unzipDir = unzipDir;
    }

    @SneakyThrows
    public void unzip() {
        String zipFilePath = this.zipFileDir + File.separator + this.zipFileName;
        System.out.println("zipFilePath = " + zipFilePath);
        ZipFile zipFile = new ZipFile(zipFilePath);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.isDirectory()) {
                System.out.print("dir  : " + entry.getName());
                String destPath = this.unzipDir + File.separator + entry.getName();
                System.out.println(" => " + destPath);
                File file = new File(destPath);
                Files.createDirectories(file.toPath());
            } else {
                String destPath = this.unzipDir + File.separator + entry.getName();

                try (InputStream inputStream = zipFile.getInputStream(entry);
                     FileOutputStream outputStream = new FileOutputStream(destPath);
                ) {
                    int data = inputStream.read();
                    while (data != -1) {
                        outputStream.write(data);
                        data = inputStream.read();
                    }
                }
                System.out.println("file : " + entry.getName() + " => " + destPath);
            }
        }
    }
}
