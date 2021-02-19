package com.javamaster.b2c.cloud.test.learn.java.bytecodetools;

import com.javamaster.b2c.cloud.test.learn.java.dynamiccompiler.DynamicClassLoader;
import com.javamaster.b2c.cloud.test.learn.java.highquality.Son;
import com.javamaster.b2c.cloud.test.learn.java.model.Person;
import javassist.*;
import javassist.bytecode.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 * Created on 2019/1/21.<br/>
 *
 * @author yudong
 */
@Slf4j
public class JavaasistTest {

    @Test
    @SneakyThrows
    public void test() {
        // 修改原有class的方法逻辑
        ClassPool pool = ClassPool.getDefault();

        // 可插入额外的classpath
        pool.insertClassPath(new ClassClassPath(this.getClass()));
        pool.insertClassPath("/usr/local/javalib");

        CtClass cc = pool.get("Son");
        cc.setSuperclass(pool.get("Person"));

        CtMethod ctMethod = cc.getDeclaredMethod("isHasLicense");
        ctMethod.insertBefore(String.format("System.out.println(\"%s方法被调用\");", ctMethod.getName()));

        CtMethod ctMethod1 = cc.getDeclaredMethod("setHasLicense");
        ctMethod1.insertBefore(String.format("System.out.println(\"%s方法被调用,参数为:\"+$1);", ctMethod.getName()));

        Class<?> aClass = cc.toClass();
        cc.writeFile("target");
        Son son = ((Son) aClass.newInstance());
        log.info("{}", son.isHasLicense());
        son.setHasLicense(true);
    }

    @Test
    @SneakyThrows
    public void test1() {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("Person");
        CtConstructor constructor = ctClass.getDeclaredConstructor(new CtClass[]{pool.get("java.lang.String")});
        // 使用-g编译可以访问的方法形参名字,也可使用$0,$1......来访问
        constructor.insertBefore("System.out.println(\"pass1 param is \"+name);");
        constructor.insertBefore("System.out.println(\"pass2 param is \"+$1);");

        CtMethod method = ctClass.getDeclaredMethod("setUnreconized",
                new CtClass[]{pool.get("java.lang.String"), pool.get("java.lang.Object")});
        method.insertBefore("{ System.out.println($1); System.out.println($2); }");

        // $args方法所有形参
        CtClass etype = ClassPool.getDefault().get("java.lang.Exception");
        method.addCatch("throw new java.lang.RuntimeException(java.util.Arrays.toString($args), $e);", etype);

        Class<?> aClass = ctClass.toClass();
        ctClass.writeFile("target");
        Person object = (Person) aClass.getDeclaredConstructor(String.class).newInstance("hello");
        object.setUnreconized("hello", "world");

    }

    @Test
    @SneakyThrows
    public void test2() {
        // 创建新的class
        ClassPool pool = ClassPool.getDefault();
        CtClass ccNew = pool.makeClass("com.javamaster.b2c.cloud.test.learn.java.highquality.GrandSon");
        ccNew.setSuperclass(pool.get("Son"));

        CtField ctField = new CtField(CtClass.intType, "dynamicAge", ccNew);
        ctField.setModifiers(Modifier.PRIVATE);
        ccNew.addField(ctField);

        CtField ctField1 = new CtField( pool.get("java.lang.ThreadLocal"), "costTimeCtThreadLocal", ccNew);
        ctField1.setModifiers(Modifier.PRIVATE);
        ccNew.addField(ctField1);

        for (CtConstructor constructor : ccNew.getConstructors()) {
            constructor.insertBefore("costTimeCtThreadLocal=new ThreadLocal();");
        }

        CtConstructor ctConstructor = CtNewConstructor.make("public GrandSon(int i){this.dynamicAge=i;}", ccNew);
        ccNew.addConstructor(ctConstructor);

        CtMethod ctMethod = CtNewMethod.make("public int getInteger() { return dynamicAge; }", ccNew);
        ccNew.addMethod(ctMethod);

        CtMethod m = CtMethod.make("public int length(int[] args) { return args.length; }", ccNew);
        m.setModifiers(m.getModifiers() | Modifier.VARARGS);
        ccNew.addMethod(m);

        Class<?> clazz = ccNew.toClass(JavaasistTest.class.getClassLoader(), null);
        ccNew.writeFile("target");
        Object object = clazz.getDeclaredConstructor(int.class).newInstance(3);
        log.info("{}", clazz.getDeclaredMethod("getInteger").invoke(object));

        // 更底层的API创建新的class
        ClassFile cf = new ClassFile(false, "com.javamaster.b2c.Foo", null);
        cf.setInterfaces(new String[]{"java.lang.Cloneable"});
        FieldInfo f = new FieldInfo(cf.getConstPool(), "width", "I");
        f.setAccessFlags(AccessFlag.PUBLIC);
        cf.addField(f);
        MethodInfo m1 = new MethodInfo(cf.getConstPool(), "getWidth", "()I");
        m1.setAccessFlags(AccessFlag.PUBLIC);
        ConstPool cp = cf.getConstPool();
        Bytecode b = new Bytecode(cp, 1, 0);
        b.addIconst(1);
        b.addReturn(CtClass.intType);
        CodeAttribute ca = b.toCodeAttribute();
        m1.setCodeAttribute(ca);
        cf.addMethod(m1);
        cf.write(new DataOutputStream(new FileOutputStream(new File("target/Foo.class"))));
    }


    @Test
    @SneakyThrows
    public void test3() {
        ClassPool pool = ClassPool.getDefault();
        String classname = "Son";

        CtClass cc = pool.get(classname);

        Loader cl = new Loader(pool);
        Class<?> c0 = cl.loadClass(classname);
        log.info("{}", c0.getClassLoader());

        Loader.Simple simple = new Loader.Simple();
        Class<?> c = simple.invokeDefineClass(cc);
        log.info("{}", c.getClassLoader());

        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(cc.toBytecode());
        Class<?> c1 = dynamicClassLoader.invokeDefineClass(classname);
        log.info("{}", c1.getClassLoader());

        DynamicClassLoader dynamicClassLoader1 = new DynamicClassLoader(cc.toBytecode());
        Class<?> c2 = dynamicClassLoader1.invokeDefineClass(classname);
        log.info("{}", c2.getClassLoader());

        Class<?> c3 = Son.class;

        log.info("{},{},{}", c == c1, c1 == c2, c3 == c);
    }
}
