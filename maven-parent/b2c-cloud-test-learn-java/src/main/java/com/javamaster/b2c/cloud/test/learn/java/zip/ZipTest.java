package com.javamaster.b2c.cloud.test.learn.java.zip;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @author yudong
 * @date 2019/7/10
 */
@Slf4j
public class ZipTest {
    static File testZipFile = null;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testZipFile = ResourceUtils.getFile("classpath:zip/test-zip-file.zip");
    }

    @Test
    @SneakyThrows
    public void testNioUnzip() {
        final Path destDir = Paths.get("target/zip");
        if (destDir.toFile().exists()) {
            FileSystemUtils.deleteRecursively(destDir.toFile());
        }
        Files.createDirectories(destDir);
        try (FileSystem fileSystem = FileSystems.newFileSystem(testZipFile.toPath(), null)) {
            Path top = fileSystem.getPath("/");
            Files.walk(top).skip(1)
                    .forEach(file -> {
                        Path target = destDir.resolve(top.relativize(file).toString());
                        try {
                            Files.copy(file, target, REPLACE_EXISTING);
                            if (file.toString().contains("taseditor_patterns.txt")) {
                                String string = FileCopyUtils.copyToString(Files.newBufferedReader(file, Charset.forName("GBK")));
                                log.info("\r\n:{}", string);
                            }
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }
    }


    @Test
    @SneakyThrows
    public void testOldUnzip() {
        final Path destDir = Paths.get("target/zip");
        if (destDir.toFile().exists()) {
            FileSystemUtils.deleteRecursively(destDir.toFile());
        }
        Files.createDirectories(destDir);
        FileUnzipper fileUnzipper = new FileUnzipper(testZipFile.getParent(), "test-zip-file.zip", destDir.toFile().getAbsolutePath());
        fileUnzipper.unzip();
    }

    @Test
    @SneakyThrows
    public void testOldZipList() {
        ZipFile zipFile = new ZipFile(testZipFile, StandardCharsets.UTF_8);
        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();
            log.info(zipEntry.getName());
            if (zipEntry.getName().contains("taseditor_patterns.txt")) {
                String string = StreamUtils.copyToString(zipFile.getInputStream(zipEntry), Charset.forName("GBK"));
                log.info("\r\n:{}", string);
            }
        }
    }

    @Test
    @SneakyThrows
    public void testOldZipRead() {
        ZipFile zipFile = new ZipFile(testZipFile, StandardCharsets.UTF_8);
        ZipEntry zipEntry = zipFile.getEntry("test-zip-file/【中文】FCEUX/tools/taseditor_patterns.txt");
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        log.info("\r\n{}", StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
    }

    @Test
    @SneakyThrows
    public void testCreateZip() {

        @Cleanup
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        @Cleanup
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

        File file = ResourceUtils.getFile("classpath:rap2-interface.json");
        putFileToZip(file, null, zipOutputStream);

        File file1 = ResourceUtils.getFile("classpath:安琪拉.jpg");
        putFileToZip(file1, null, zipOutputStream);

        File file2 = ResourceUtils.getFile("classpath:jvm/jvm-commands.sh");
        putFileToZip(file2, "jvm", zipOutputStream);

        zipOutputStream.finish();
        Path path = Paths.get("target/压缩文件.zip");
        Files.write(path, byteArrayOutputStream.toByteArray());
    }

    @SneakyThrows
    private void putFileToZip(File file, String dir, ZipOutputStream zipOutputStream) {
        String str = file.getName();
        if (dir != null) {
            str = dir + "/" + file.getName();
        }
        ZipEntry zipEntry = new ZipEntry(str);
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(Files.readAllBytes(file.toPath()));
        zipOutputStream.closeEntry();
    }

    @Test
    @SneakyThrows
    public void testGzip() {
        final Path destDir = Paths.get("target/gzip");
        if (destDir.toFile().exists()) {
            FileSystemUtils.deleteRecursively(destDir.toFile());
        }
        Files.createDirectories(destDir);
        try (
                FileOutputStream outputStream = new FileOutputStream(new File(destDir.toFile(), "gzip.zip"));
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)
        ) {
            File file = ResourceUtils.getFile("classpath:book.json");
            byte[] data = StreamUtils.copyToByteArray(new FileInputStream(file));
            gzipOutputStream.write(data);
            gzipOutputStream.finish();
        }
    }

    @Test
    @SneakyThrows
    public void testGzipUnzip() {
        final Path destDir = Paths.get("target/gzip/gzip.zip");
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(destDir.toFile()))) {
            String s = StreamUtils.copyToString(gzipInputStream, StandardCharsets.UTF_8);
            log.info("\r\n{}", s);
        }
    }
}
