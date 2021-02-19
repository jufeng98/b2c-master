package com.javamaster.b2c.cloud.test.learn.java.bytecode;

import com.javamaster.b2c.cloud.test.learn.java.bytecode.modifier.ClassModifier;
import com.javamaster.b2c.cloud.test.learn.java.bytecode.modifier.FieldModifier;
import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * Created on 2019/1/18.<br/>
 *
 * @author yudong
 */
public class AnalyzeClassFileTest {
    @Test
    public void test() throws Exception {
        File file = ResourceUtils
                .getFile("classpath:com/javamaster/b2c/cloud/test/learn/java/bytecode/ExampleCLass.class");

        // ClassFileReader classFileReader = new ClassFileReader(new FileInputStream(file));
        //
        // System.out.println(classFileReader);
        // System.out.println(classFileReader.getClassName());
        // System.out.println(classFileReader.getSuperClassName());
        // System.out.println(classFileReader.getInterfaceNames());
        System.out.println("---");

        ClassReader classReader = new ClassReader(new FileInputStream(file));
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        System.out.println("魔数:0X" + Integer.toHexString(classReader.readInt(0)).toUpperCase());
        System.out.println("次版本号:" + classReader.readShort(4));
        classReader.accept(new ClassAdapter(classWriter) {
            //visit [ visitSource ] [ visitOuterClass ] ( visitAnnotation | visitAttribute )* ( visitInnerClass | visitField | visitMethod )* visitEnd

            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                System.out.println("主版本号:" + version);
                System.out.println("访问权限:0X" + Integer.toHexString(access) + " " + ClassModifier.getModifiers((short) access));
                System.out.println("名字:" + name);
                System.out.println("签名:" + signature);
                System.out.println("父名字:" + superName);
                System.out.println("接口列表:" + Arrays.toString(interfaces));
                super.visit(version, access, name, signature, superName, interfaces);
            }

            @Override
            public void visitSource(String source, String debug) {
                super.visitSource(source, debug);
            }

            @Override
            public void visitOuterClass(String owner, String name, String desc) {
                super.visitOuterClass(owner, name, desc);
            }

            @Override
            public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                System.out.println("注解:" + desc + " 运行时可见性:" + visible);
                return super.visitAnnotation(desc, visible);
            }

            @Override
            public void visitAttribute(Attribute attribute) {
                super.visitAttribute(attribute);
            }

            @Override
            public void visitInnerClass(String name, String outerName, String innerName, int access) {
                super.visitInnerClass(name, outerName, innerName, access);
            }

            @Override
            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                System.out.println("字段访问权限:0X:" + Integer.toHexString(access).toUpperCase() + " " + FieldModifier.getModifiers((short) access));
                System.out.println("字段名:" + name);
                System.out.println("字段描述:" + desc);
                System.out.println("字段签名:" + signature);
                System.out.println("字段值:" + value);
                FieldVisitor fieldVisitor = super.visitField(access, name, desc, signature, value);
                return fieldVisitor;
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                System.out.println("方法访问权限:0X:" + Integer.toHexString(access).toUpperCase() + " " + FieldModifier.getModifiers((short) access));
                System.out.println("方法名:" + name);
                System.out.println("方法描述:" + desc);
                System.out.println("方法签名:" + signature);
                System.out.println("方法异常类型声明:" + Arrays.toString(exceptions));
                MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
                return methodVisitor;
            }

            @Override
            public void visitEnd() {
                super.visitEnd();
            }
        }, ClassReader.SKIP_DEBUG);
    }
}
