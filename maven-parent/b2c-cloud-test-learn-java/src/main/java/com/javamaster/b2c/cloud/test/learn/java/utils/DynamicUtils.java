package com.javamaster.b2c.cloud.test.learn.java.utils;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.ClassFileReader;
import com.javamaster.b2c.cloud.test.learn.java.dynamiccompiler.ByteArrayJavaClass;
import com.javamaster.b2c.cloud.test.learn.java.dynamiccompiler.DynamicClassLoader;
import com.javamaster.b2c.cloud.test.learn.java.dynamiccompiler.StringBuilderJavaSource;

import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created on 2019/1/25.<br/>
 *
 * @author yudong
 */
public class DynamicUtils {

    static Logger logger = Logger.getLogger(DynamicUtils.class.getCanonicalName());

    public static Object compileAndExecuteSourceCode(String code) {
        List<Map<String, Object>> finalList = new ArrayList<>();
        String packageName = getPackageName(code);
        String className = getClassName(code);
        String compileClassName = packageName + "." + className;
        final String LS = System.getProperty("line.separator");
        final String CLASS_PATH = System.getProperty("java.class.path");
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            List<ByteArrayJavaClass> classFileObjects = new ArrayList<>();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            JavaFileManager newFileManager = new ForwardingJavaFileManager<JavaFileManager>(fileManager) {
                @Override
                public JavaFileObject getJavaFileForOutput(Location location, String className,
                                                           JavaFileObject.Kind kind, FileObject sibling) {
                    ByteArrayJavaClass fileObject = new ByteArrayJavaClass(className);
                    classFileObjects.add(fileObject);
                    return fileObject;
                }

            };

            StringBuilder sb = new StringBuilder();
            sb.append(code);
            sb.append(LS);
            Map<String, Object> map = new HashMap<>(10);
            map.put("源码信息:", sb.toString());
            logger.info("源码信息:" + sb.toString());
            finalList.add(map);

            // 设置编译参数
            ArrayList<String> ops = new java.util.ArrayList<>();
            ops.add("-Xlint:unchecked");
            // 设置classpath
            ops.add("-classpath");
            ops.add(".;" + CLASS_PATH);

            JavaFileObject source = new StringBuilderJavaSource(compileClassName, sb);
            JavaCompiler.CompilationTask task = compiler.getTask(null, newFileManager, diagnostics, ops, null,
                    Arrays.asList(source));
            task.call();
            newFileManager.close();

            Map<String, Object> map2 = new java.util.HashMap<>();
            map2.put("编译信息:", diagnostics.getDiagnostics().toString());
            logger.info("编译信息:" + diagnostics.getDiagnostics().toString());
            finalList.add(map2);

            byte[] bytes = classFileObjects.get(0).getbytes();
            String name = classFileObjects.get(0).getName().substring(1);
            String tmpDir = System.getProperty("java.io.tmpdir");
            File file = new File(tmpDir, name.replace(".", "-") + ".class");
            if (file.exists()) {
                file.delete();
            }
            Files.write(file.toPath(), bytes);

            ClassLoader loader = new DynamicClassLoader(bytes);
            Class<?> c = loader.loadClass(name);
            Method method = c.getMethod("execute");
            Object target = c.newInstance();
            Object returnObject = method.invoke(target);

            HashMap<String, Object> map3 = new HashMap<>(10);
            map3.put("方法调用结果:", returnObject);
            logger.info("方法调用结果:" + returnObject);
            finalList.add(map3);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> map4 = new HashMap<>(10);
            map4.put("错误信息:" + e.getCause().getMessage(), e.getCause());
            logger.warning("错误信息:" + e.getCause().getMessage());
            finalList.add(map4);
        }
        return finalList;
    }

    public static Object executeByteCode(byte[] byteCode) {
        List<Map<String, Object>> finalList = new ArrayList<>();
        try {
            ClassLoader loader = new DynamicClassLoader(byteCode);
            ClassFileReader classFileReader = new ClassFileReader(new ByteArrayInputStream(byteCode));
            Class<?> c = loader.loadClass(classFileReader.getClassName());
            Method method = c.getMethod("execute");
            Object target = c.newInstance();
            Object returnObject = method.invoke(target);
            HashMap<String, Object> map3 = new HashMap<>(10);
            map3.put("方法调用结果:", returnObject);
            logger.info("方法调用结果:" + returnObject);
            finalList.add(map3);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("错误信息:" + e.getCause().getMessage());
            Map<String, Object> map4 = new HashMap<>(10);
            map4.put("错误信息:" + e.getCause().getMessage(), e.getCause());
            finalList.add(map4);
        }
        return finalList;
    }

    private static String getPackageName(String code) {
        return code.substring(8, code.indexOf(';'));
    }

    private static String getClassName(String code) {
        return code.substring(code.indexOf("class") + "class".length(), code.indexOf("{")).trim();
    }
}
