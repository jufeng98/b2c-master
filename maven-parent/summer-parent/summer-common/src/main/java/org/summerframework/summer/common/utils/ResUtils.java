package org.summerframework.summer.common.utils;

import static org.summerframework.summer.common.utils.ClassUtils.FILE_PREFIX;
import static org.summerframework.summer.common.utils.ClassUtils.JAR_PREFIX;
import static org.summerframework.summer.common.utils.ClassUtils.getDefaultClassLoader;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author yudong
 * @date 2019/5/18
 */
public class ResUtils {


    public static File getFile(PackageType packageType, String fileName) {
        URL url = null;
        ClassLoader cl = getDefaultClassLoader();
        switch (packageType) {
            case META_INF:
                fileName = "/" + packageType.name + fileName;
                url = cl.getResource(fileName);
                break;
            case CLASSES:
                url = cl.getResource(fileName);
                break;
            default:
                break;
        }
        return new File(url.getFile());
    }

    public static List<File> getFiles(PackageType packageType, String fileName) {
        try {
            List<File> files = new ArrayList<>();
            ClassLoader classLoader = getDefaultClassLoader();
            if (packageType == PackageType.META_INF) {
                fileName = packageType.name + fileName;
            } else if (packageType == PackageType.CLASSES) {
            }
            Enumeration<URL> urls = classLoader.getResources(packageType.name + fileName);
            while ((urls.hasMoreElements())) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if (FILE_PREFIX.equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.name());
                    files.add(new File(filePath));
                } else if (JAR_PREFIX.equals(protocol)) {
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    Enumeration<JarEntry> entryEnumeration = jar.entries();
                    while (entryEnumeration.hasMoreElements()) {
                        JarEntry jarEntry = entryEnumeration.nextElement();
                        String name = jarEntry.getName();
                        if (name.startsWith("/")) {
                            name = name.substring(1);
                        }
                        if (jarEntry.isDirectory() || !name.contains(fileName)) {
                            continue;
                        }
                        files.add(new File(name));
                    }
                }
            }
            return files;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public enum PackageType {
        META_INF("META-INF/"),
        CLASSES("classes");
        public String name;

        PackageType(String name) {
            this.name = name;
        }
    }

}
