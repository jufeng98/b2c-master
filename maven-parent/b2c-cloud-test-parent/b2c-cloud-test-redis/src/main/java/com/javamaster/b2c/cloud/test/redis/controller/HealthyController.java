package com.javamaster.b2c.cloud.test.redis.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring/test")
public class HealthyController {
    private static Logger logger = LoggerFactory.getLogger(HealthyController.class);

    public static void main(String[] args) {
        String string = "abcabcabcabc";
        Pattern pattern = Pattern.compile("\\w");
        Matcher matcher = pattern.matcher(string);
        System.out.println(matcher.lookingAt());
        System.out.println(matcher.matches());
        while (matcher.find()) {
            System.out.println(matcher.start() + " " + matcher.end() + " " + matcher.group());
        }


    }

    @RequestMapping("/1")
    @ResponseBody
    public String healthy() throws Exception {
        logger.info("调用HealthyController portal-1 检查正常");
        return "1";
    }

    @ResponseBody
    @RequestMapping(value = "pie", produces = {"application/json"})
    public String testCompiler(HttpServletRequest request, String mainCode) {
        java.util.List<java.util.Map<String, Object>> finalList = new java.util.ArrayList<java.util.Map<String, Object>>();
        String packageName = getClass().getPackage().getName();
        String complateClassName = packageName + ".DynamicClass";
        final String LS = System.getProperty("line.separator");
        final String CLASS_PATH = System.getProperty("java.class.path");
        try {
            class StringBuilderJavaSource extends javax.tools.SimpleJavaFileObject {
                private StringBuilder code;

                public StringBuilderJavaSource(String name, StringBuilder code) {
                    super(java.net.URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension),
                            Kind.SOURCE);
                    this.code = code;
                }

                @Override
                public CharSequence getCharContent(boolean ignoreEncodingErrors) throws java.io.IOException {
                    return code;
                }

            }
            class ByteArrayJavaClass extends javax.tools.SimpleJavaFileObject {
                private java.io.ByteArrayOutputStream stream;

                public ByteArrayJavaClass(String name) {
                    super(java.net.URI.create("bytes:///" + name), Kind.CLASS);
                    stream = new java.io.ByteArrayOutputStream();
                }

                @Override
                public java.io.OutputStream openOutputStream() {
                    return stream;
                }

                public byte[] getbytes() {
                    return stream.toByteArray();
                }
            }
            class DynamicClassLoader extends ClassLoader {
                private byte[] classBytes;

                public DynamicClassLoader(byte[] classBytes) {
                    super(DynamicClassLoader.class.getClassLoader());
                    this.classBytes = classBytes;
                }

                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
                    return cl;
                }

            }

            javax.tools.JavaCompiler compiler = javax.tools.ToolProvider.getSystemJavaCompiler();
            final java.util.List<ByteArrayJavaClass> classFileObjects = new java.util.ArrayList<ByteArrayJavaClass>();
            final javax.tools.DiagnosticCollector<javax.tools.JavaFileObject> diagnostics = new javax.tools.DiagnosticCollector<javax.tools.JavaFileObject>();
            javax.tools.JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            fileManager = new javax.tools.ForwardingJavaFileManager<javax.tools.JavaFileManager>(fileManager) {

                @Override
                public javax.tools.JavaFileObject getJavaFileForOutput(Location location, String className,
                                                                       javax.tools.JavaFileObject.Kind kind, javax.tools.FileObject sibling)
                        throws java.io.IOException {
                    ByteArrayJavaClass fileObject = new ByteArrayJavaClass(className);
                    classFileObjects.add(fileObject);
                    return fileObject;
                }

            };
            final StringBuilder sb = new StringBuilder();
            sb.append("package " + packageName + ";" + LS);
            sb.append(mainCode);
            sb.append(LS);
            java.util.HashMap<String, Object> map = new java.util.HashMap<String, Object>();
            map.put("源码信息:", sb.toString());
            logger.info("源码信息:" + sb.toString());
            finalList.add(map);
            StringBuilder allPaths = new StringBuilder();
            // 设置编译参数
            java.util.ArrayList<String> ops = new java.util.ArrayList<String>();
            ops.add("-Xlint:unchecked");
            String reqUrl = request.getRequestURL().toString();
            if (reqUrl.contains("B2C40")) {

            } else if (reqUrl.contains("b2c")) {

            } else {
                String bootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                bootPath = bootPath.replace("!", "").substring(bootPath.indexOf("file:") + 5).substring(0,
                        bootPath.indexOf(".jar") - 1);
                logger.info(bootPath);
                byte[] bytes = new byte[10240];
                String tmpDir = System.getProperty("java.io.tmpdir");
                File file = new File(bootPath);
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> enumeration = jarFile.entries();

                File delFile = new File(tmpDir, "BOOT-INF");
                allPaths.append(delFile.getAbsolutePath() + "/classes;");
//				while (enumeration.hasMoreElements()) {
//					JarEntry jarEntry = (JarEntry) enumeration.nextElement();
//					if (!jarEntry.getName().contains("BOOT-INF")) {
//						continue;
//					}
//					if (jarEntry.getName().endsWith("class") || jarEntry.getName().endsWith(".jar")) {
//						System.out.println(jarEntry.getName());
//						BufferedInputStream inputStream = new BufferedInputStream(jarFile.getInputStream(jarEntry));
//						File path = new File(tmpDir,
//								jarEntry.getName().substring(0, jarEntry.getName().lastIndexOf("/")));
//						path.mkdirs();
//						File file2 = new File(tmpDir, jarEntry.getName());
//						BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2));
//						while ((inputStream.read(bytes)) != -1) {
//							// outputStream.write(bytes);
//						}
//						inputStream.close();
//						outputStream.close();
//					}
//
//				}
                jarFile.close();
                File libFile = new File(delFile, "lib");
                for (File f : libFile.listFiles()) {
                    allPaths.append(f.getAbsolutePath() + ";");
                }
            }
            logger.info("pathsss:" + allPaths.toString());
            // 设置classpath
            ops.add("-classpath");
            ops.add(".;" + allPaths.toString() + CLASS_PATH);
            javax.tools.JavaFileObject source = new StringBuilderJavaSource(complateClassName, sb);
            javax.tools.JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, ops, null,
                    java.util.Arrays.asList(source));
            task.call();
            fileManager.close();
            java.util.HashMap<String, Object> map2 = new java.util.HashMap<String, Object>();
            map2.put("编译信息:", diagnostics.getDiagnostics().toString());
            logger.info("编译信息:" + diagnostics.getDiagnostics().toString());
            finalList.add(map2);
            ClassLoader loader = new DynamicClassLoader(classFileObjects.get(0).getbytes());
            Class<?> c = loader.loadClass(classFileObjects.get(0).getName().substring(1));
            java.lang.reflect.Method method = c.getMethod("debug");
            Object target = c.newInstance();
            final Object returnobject = method.invoke(target);
            java.util.HashMap<String, Object> map3 = new java.util.HashMap<String, Object>();
            map3.put("方法调用结果:", returnobject);
            logger.info("方法调用结果:" + returnobject);
            finalList.add(map3);
        } catch (final Exception e) {
            java.util.HashMap<String, Object> map4 = new java.util.HashMap<String, Object>();
            map4.put("错误信息:" + e.getMessage(), e.getCause());
            logger.info("错误信息:" + e.getMessage(), e);
            finalList.add(map4);
        }
        return com.alibaba.fastjson.JSONObject.toJSONString(finalList, true);
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
