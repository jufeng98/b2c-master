package com.javamaster.b2c.cloud.test.pattern.effectivejava.multithread;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created on 2018/8/13.<br/>
 *
 * @author yudong
 */
public class FileIterator implements Callable<List<String>> {

    private String path;

    public FileIterator(String path) {
        this.path = path;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> list = new ArrayList<>();
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String path = file.toAbsolutePath().toString();
                System.out.println(path);
                if (path.contains("考试")) {
                    list.add(path);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        return list;
    }
}
