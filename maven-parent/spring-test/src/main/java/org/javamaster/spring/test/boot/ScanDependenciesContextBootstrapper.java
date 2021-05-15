package org.javamaster.spring.test.boot;

import lombok.SneakyThrows;
import org.javamaster.spring.test.annos.ScanTestedDependencies;
import org.javamaster.spring.test.utils.TestUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.web.WebTestContextBootstrapper;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2021/5/15
 */
public class ScanDependenciesContextBootstrapper extends WebTestContextBootstrapper {

    private final Set<Class<?>> alreadyHandle = new HashSet<>();
    private Vector<?> allAppClasses;

    @Override
    protected MergedContextConfiguration processMergedContextConfiguration(MergedContextConfiguration mergedConfig) {
        MergedContextConfiguration mergedContextConfiguration = super.processMergedContextConfiguration(mergedConfig);
        Class<?> testClass = mergedConfig.getTestClass();
        ScanTestedDependencies annotation = testClass.getAnnotation(ScanTestedDependencies.class);

        Class<?> targetTestedClass = annotation.value();

        initAllAppClasses(targetTestedClass.getClassLoader());

        List<Class<?>> list = getRelateClasses(targetTestedClass);
        list = list.stream()
                .filter(clz -> !clz.getName().startsWith("org.springframework") && !Modifier.isAbstract(clz.getModifiers()))
                .collect(Collectors.toList());

        Class<?>[] classes = mergedConfig.getClasses();
        Class<?>[] appClasses = list.toArray(new Class<?>[]{});

        Class<?>[] allClasses = new Class[classes.length + appClasses.length];
        System.arraycopy(classes, 0, allClasses, 0, classes.length);
        System.arraycopy(appClasses, 0, allClasses, classes.length, appClasses.length);

        TestUtils.reflectSet(mergedContextConfiguration, "classes", allClasses);
        return mergedContextConfiguration;
    }

    private List<Class<?>> getRelateClasses(Class<?> clz) {
        Field[] declaredFields = clz.getDeclaredFields();
        return getFieldRelateClasses(declaredFields);
    }

    private List<Class<?>> getFieldRelateClasses(Field[] declaredFields) {
        List<Class<?>> list = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            if (declaredField.getAnnotation(Autowired.class) == null) {
                continue;
            }
            Class<?> fieldTypeClass = declaredField.getType();
            if (alreadyHandle.contains(fieldTypeClass)) {
                continue;
            }
            alreadyHandle.add(fieldTypeClass);
            if (fieldTypeClass.isInterface()) {
                List<Class<?>> allSubClass = getInterfaceImplClass(fieldTypeClass);
                list.addAll(allSubClass);
                for (Class<?> subClass : allSubClass) {
                    List<Class<?>> relateClasses = getFieldRelateClasses(subClass.getDeclaredFields());
                    list.addAll(relateClasses);
                }
            } else {
                list.add(fieldTypeClass);
                List<Class<?>> relateClasses = getFieldRelateClasses(fieldTypeClass.getDeclaredFields());
                list.addAll(relateClasses);
            }
        }
        return list;
    }


    @SneakyThrows
    private List<Class<?>> getInterfaceImplClass(Class<?> interfaceClass) {
        if (interfaceClass == Logger.class) {
            return Collections.emptyList();
        }
        List<Class<?>> allSubclass = new ArrayList<>();
        for (Object o : allAppClasses) {
            Class<?> c = (Class<?>) o;
            if (interfaceClass.isAssignableFrom(c) && !c.isInterface()) {
                allSubclass.add(c);
            }
        }
        return allSubclass;
    }

    @SneakyThrows
    private void initAllAppClasses(ClassLoader classLoader) {
        String testClassPath = Objects.requireNonNull(classLoader.getResource("")).getFile();
        File targetPath = new File(testClassPath).getParentFile();
        File classPath = new File(targetPath, "classes");
        String classPathStr = classPath.toString();
        Files.walkFileTree(classPath.toPath(), new SimpleFileVisitor<Path>() {
            @SneakyThrows
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.getFileName().toString().endsWith(".class")) {
                    String str = file.toString()
                            .replace(classPathStr, "")
                            .replace(".class", "")
                            .replace("\\", ".");
                    Class.forName(str.substring(1));
                }
                return FileVisitResult.CONTINUE;
            }
        });

        Class<?> clazz = classLoader.getClass();
        while (clazz != ClassLoader.class) {
            clazz = clazz.getSuperclass();
        }
        Field field = clazz.getDeclaredField("classes");
        field.setAccessible(true);
        allAppClasses = (Vector<?>) field.get(classLoader);
    }

}
