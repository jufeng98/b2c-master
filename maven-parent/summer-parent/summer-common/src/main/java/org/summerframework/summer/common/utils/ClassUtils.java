package org.summerframework.summer.common.utils;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author yudong
 * @date 2019/5/18
 */
public class ClassUtils {
    public static List<Class<?>> findSubClasses(Set<Class<?>> classes, Class<?> parentClz) {
        List<Class<?>> list = new ArrayList<>(5);
        for (Class<?> c : classes) {
            if (parentClz.isAssignableFrom(c) && c != parentClz) {
                list.add(c);
            }
        }
        return list;
    }

    public static String beanName(Class<?> clz) {
        String name = clz.getSimpleName();
        if ("".equals(name)) {
            return "";
        }
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }


    private static Map<String, Set<Class<?>>> classCacheMap = new HashMap<>();
    public static final String FILE_PREFIX = "file";
    public static final String JAR_PREFIX = "jar";

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
            Enumeration<URL> urls = getDefaultClassLoader().getResources(pkgDirName);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if (FILE_PREFIX.equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.name());
                    findClassesByFilePath(filePath, classes);
                } else if (JAR_PREFIX.equals(protocol)) {
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    findClassesByJarPath(packageName, jar, classes);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        classCacheMap.put(packageName, classes);
        return classes;
    }

    private static final String CLASS_SUFFIX = ".class";
    private static final String CLASSES_SUFFIX = "classes";

    private static void findClassesByFilePath(String pkgPath, Set<Class<?>> classes) throws Exception {
        File dir = new File(pkgPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                File classFile = path.toFile();
                int startIndex = classFile.getAbsolutePath().indexOf(CLASSES_SUFFIX) + CLASSES_SUFFIX.length() + 1;
                int endIndex = classFile.getAbsolutePath().indexOf(CLASS_SUFFIX);
                String className = classFile.getAbsolutePath().substring(startIndex, endIndex);
                Class clz = loadClass(className.replace(File.separator, "."));
                classes.add(clz);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void findClassesByJarPath(String pkgName, JarFile jar, Set<Class<?>> classes) {
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
            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(CLASS_SUFFIX)) {
                continue;
            }
            className = name.substring(0, name.length() - 6);
            clz = loadClass(className.replace("/", "."));
            classes.add(clz);
        }
    }

    private static Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl != null) {
            return cl;
        }
        cl = ClassUtils.class.getClassLoader();
        if (cl != null) {
            return cl;
        } else {
            throw new RuntimeException("no classLoader found");
        }
        // cl = ClassLoader.getSystemClassLoader();
        // return cl;
    }

}
