package com.javamaster.b2c.cloud.test.learn.java.nio;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Future;

/**
 * @author yudong
 * @date 2019/7/22
 */
public class NioTest {
    @Test
    public void test() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        RandomAccessFile aFile = new RandomAccessFile(file.getAbsolutePath(), "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    @Test
    public void test1() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        RandomAccessFile aFile = new RandomAccessFile(file.getAbsolutePath(), "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                char c = (char) buf.get();
                System.out.print(c);
                if (c == '1') {
                    buf.mark();
                }
            }
            System.out.println("\r\n------");
            buf.reset();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    @Test
    public void test2() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        RandomAccessFile fromFile = new RandomAccessFile(file.getAbsolutePath(), "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile(file.getParent() + "/toFile.sql", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        // toChannel.transferFrom(fromChannel, position, count);
        // 或者
        fromChannel.transferTo(position, count, toChannel);
    }

    @Test
    public void test3() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        RandomAccessFile fromFile = new RandomAccessFile(file.getParent() + "/toFile.txt", "rw");
        FileChannel channel = fromFile.getChannel();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while (buf.hasRemaining()) {
            channel.write(buf);
        }
        channel.close();
    }

    @Test
    public void test4() {
        Path path = Paths.get("c:\\data\\myfile.txt");
        System.out.println(path);

        path = Paths.get("/home/jakobjenkov/myfile.txt");
        System.out.println(path);

        Path projects = Paths.get("d:\\data", "projects");
        System.out.println(projects);
        Path file = Paths.get("d:\\data", "projects\\a-project\\myfile.txt");
        System.out.println(file);

        Path currentDir = Paths.get("");
        System.out.println(currentDir.toAbsolutePath());
        currentDir = Paths.get("d:\\data\\projects\\.\\a-project");
        System.out.println(currentDir.toAbsolutePath());
        Path parentDir = Paths.get("..");
        System.out.println(parentDir.toAbsolutePath());

        String originalPath = "d:\\data\\projects\\a-project\\..\\another-project";

        Path path1 = Paths.get(originalPath);
        System.out.println("path1 = " + path1);

        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);

    }

    @Test
    public void test5() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        Path rootPath = file.getParentFile().toPath();
        String fileToFind = File.separator + "README.txt";
        Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                String fileString = file.toAbsolutePath().toString();
                System.out.println("pathString = " + fileString);
                if (fileString.endsWith(fileToFind)) {
                    System.out.println("file found at path: " + file.toAbsolutePath());
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void test6() throws Exception {
        File file = ResourceUtils.getFile("classpath:template/Controller.mustache");
        Path rootPath = file.getParentFile().toPath();
        Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("delete file: " + file.toString());
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                System.out.println("delete dir: " + dir.toString());
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void test7() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        Future<Integer> operation = fileChannel.read(buffer, position);
        while (!operation.isDone()) {
        }
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
    }

    @Test
    public void test8() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }


    @Test
    public void test9() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        buffer.put("test data".getBytes());
        buffer.flip();
        Future<Integer> operation = fileChannel.write(buffer, position);
        buffer.clear();
        while (!operation.isDone()) {
        }
        System.out.println("Write done");
    }

    @Test
    public void test10() throws Exception {
        File file = ResourceUtils.getFile("classpath:derby-script.sql");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(file.toPath(), StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        buffer.put("test data".getBytes());
        buffer.flip();
        fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("bytes written: " + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("Write failed");
                exc.printStackTrace();
            }
        });
    }
}
