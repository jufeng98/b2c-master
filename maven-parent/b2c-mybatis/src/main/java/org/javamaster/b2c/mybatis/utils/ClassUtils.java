package org.javamaster.b2c.mybatis.utils;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2019/5/23
 */
public class ClassUtils {

    private static Map<String, Set<Class<?>>> classCacheMap = new HashMap<>();
    private static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private static MetadataReaderFactory metaReader = new CachingMetadataReaderFactory();

    @SneakyThrows
    public static Set<Class<?>> getAllClassesFromPackage(String packageName) {
        String dir = packageName.replace(".", "/");
        Resource[] resources;
        try {
            resources = resourcePatternResolver.getResources("classpath*:" + dir + "/**/*.class");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.stream(resources).map(resource -> {
            try {
                return Class.forName(metaReader.getMetadataReader(resource).getClassMetadata().getClassName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());
    }

    /**
     * 从package中获取所有的Class
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassesFromPackage(String packageName) {
        Set<Class<?>> classes = classCacheMap.get(packageName);
        if (classes != null) {
            return classes;
        }
        classes = new LinkedHashSet<>();
        String pkgDirName = packageName.replace('.', '/');
        try {
            Enumeration<URL> urls = ClassUtils.class.getClassLoader().getResources(pkgDirName);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.name());
                    findClassesByFile(packageName, filePath, classes);
                } else if ("jar".equals(protocol)) {
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    findClassesByJar(packageName, jar, classes);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        classCacheMap.put(packageName, classes);
        return classes;
    }

    private static void findClassesByFile(String pkgName, String pkgPath, Set<Class<?>> classes) throws Exception {
        File dir = new File(pkgPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                String className = file.toFile().getName();
                className = className.substring(0, className.length() - 6);
                Class clz = loadClass(pkgName + "." + className);
                classes.add(clz);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void findClassesByJar(String pkgName, JarFile jar, Set<Class<?>> classes) {
        String pkgDir = pkgName.replace(".", "/");
        Enumeration<JarEntry> entry = jar.entries();
        JarEntry jarEntry;
        String name, className;
        Class<?> clz;
        while (entry.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录和一些jar包里的其他文件,如META-INF等
            jarEntry = entry.nextElement();
            name = jarEntry.getName();
            if (name.startsWith("/")) {
                name = name.substring(1);
            }
            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                continue;
            }
            className = name.substring(0, name.length() - 6);
            clz = loadClass(className.replace("/", "."));
            classes.add(clz);
        }
    }

    private static Class<?> loadClass(String fullClzName) {
        try {
            return Class.forName(fullClzName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
