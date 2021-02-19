package org.javamaster.annotation.processor.processor;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;
import org.javamaster.annotation.processor.service.RunOnce;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * <p>\u53ea\u80fd\u5b58\u5728\u4e00\u4e2a\u652f\u6301\u6240\u6709\u6ce8\u89e3\u7684\u5904\u7406\u7c7b</p>
 * Created on 2019/1/18.<br/>
 *
 * @author yudong
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class RunOnceProcessor extends AbstractProcessor {
    private Messager messager;
    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;
    private ProcessingEnvironment processingEnv;
    private Elements elementUtils;
    private Types typeUtils;
    private Filer filer;

    private boolean firstRoundFinish = false;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.processingEnv = processingEnv;
        this.elementUtils = processingEnv.getElementUtils();
        this.typeUtils = processingEnv.getTypeUtils();
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("RunOnceProcessor start running");
        if (firstRoundFinish) {
            return false;
        }
        try {
            List<Class<? extends RunOnce>> list = getAllSubclassOfRunOnce();
            for (Class<? extends RunOnce> runOnceClass : list) {
                runOnceClass.newInstance().run(processingEnv, annotations, roundEnv);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        firstRoundFinish = true;
        return false;
    }

    @SuppressWarnings("unchecked")
    private <T> List<Class<? extends T>> getAllSubclassOfRunOnce() throws Exception {
        ClassLoader classLoader = RunOnce.class.getClassLoader();
        String packageName = RunOnce.class.getPackage().getName();
        URL url = classLoader.getResource("");
        System.out.println("url:" + url);
        System.out.println("package:" + packageName);
        assert url != null;
        String path = url.toString().replace("file:/", "");
        File file = new File(path, packageName.replace(".", "/"));
        file.listFiles((File dir, String name) -> {
            try {
                Class.forName(packageName + "." + name.replace(".class", ""));
            } catch (ClassNotFoundException ignored) {
            }
            return true;
        });
        Class.forName(packageName + ".ClassChecker");
        Class.forName(packageName + ".GenerateClassFromTables");

        Class<?> clazz = classLoader.getClass();
        while (clazz != ClassLoader.class) {
            clazz = clazz.getSuperclass();
        }
        Field field = clazz.getDeclaredField("classes");
        field.setAccessible(true);
        Vector v = (Vector) field.get(classLoader);
        List<Class<? extends T>> allSubclass = new ArrayList<>();
        for (Object o : v) {
            Class<?> c = (Class<?>) o;
            if (RunOnce.class.isAssignableFrom(c) && !c.isInterface()) {
                allSubclass.add((Class<? extends T>) c);
            }
        }
        return allSubclass;
    }

}
