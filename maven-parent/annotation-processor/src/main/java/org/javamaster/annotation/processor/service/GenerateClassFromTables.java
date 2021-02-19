package org.javamaster.annotation.processor.service;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

/**
 * Created on 2019/1/22.<br/>
 *
 * @author yudong
 */
public class GenerateClassFromTables implements RunOnce {
    @Override
    public void run(ProcessingEnvironment processingEnv, Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            System.out.println("start to generate class from tables");

            ClassLoader cl = this.getClass().getClassLoader();
            URL url = (cl != null ? cl.getResource("Tables.txt") : ClassLoader.getSystemResource("Tables.txt"));
            if (url == null) {
                System.out.println("Tables.txt file not exists.");
                return;
            }
            File file = new File(url.toString().replace("file:/", ""));
            if (!file.exists()) {
                System.out.println("Tables.txt file not exists");
                return;
            }
            List<String> lines = Files.readAllLines(file.toPath());
            String packageName = lines.remove(0);
            List<Integer> sep = new ArrayList<>();
            for (int k = 0; k < lines.size(); k++) {
                if (lines.get(k).contains("---")) {
                    sep.add(k);
                }
            }
            for (int h = 0; h < sep.size(); h++) {
                if (h + 1 > sep.size()) {
                    break;
                }
                List<String> tmpList;
                if (h + 1 < sep.size()) {
                    tmpList = lines.subList(sep.get(h), sep.get(h + 1));
                } else {
                    tmpList = lines.subList(sep.get(h), lines.size());
                }
                StringBuilder stringBuilder = new StringBuilder();
                String tableName = tmpList.get(1);
                for (int q = 2; q < tmpList.size(); q++) {
                    String[] typeInfo = tmpList.get(q).trim().split("\\s+");
                    stringBuilder.append("private ").append(typeInfo[0]).append(" ").append(typeInfo[1]).append(";");
                }
                JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile(tableName);
                PrintWriter printWriter = new PrintWriter(javaFileObject.openWriter());
                printWriter.println("package " + packageName + ";");
                printWriter.println("import org.javamaster.annotation.processor.annotation.GenerateGetMethod;");
                printWriter.println("@GenerateGetMethod");
                printWriter.println("public class " + tableName + " {");
                printWriter.println(stringBuilder.toString());
                printWriter.println("}");
                printWriter.close();
                System.out.println("generate class from tables finished");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = GenerateClassFromTables.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }
}
