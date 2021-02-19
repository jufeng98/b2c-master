package com.javamaster.b2c.cloud.test.learn.java.bytecodetools;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created on 2019/1/21.<br/>
 *
 * @author yudong
 */
@Slf4j
public class AsmTest {

    @Test
    @SneakyThrows
    public void test() {
        ClassReader classReader = new ClassReader("Person");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        JavaClassAdapter javaClassAdapter = new JavaClassAdapter(classWriter);
        classReader.accept(javaClassAdapter, ClassReader.SKIP_DEBUG);

        // 插入新方法
        MethodVisitor helloVisitor = classWriter
                .visitMethod(Opcodes.ACC_PUBLIC, "sayHello", "()V", null, new String[]{"javax.validation.ValidationException"});
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();

        byte[] bytes = classWriter.toByteArray();

        File file = new File("target", "PersonModify.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

}
