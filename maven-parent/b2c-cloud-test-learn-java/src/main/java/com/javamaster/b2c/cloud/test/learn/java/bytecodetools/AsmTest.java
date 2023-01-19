package com.javamaster.b2c.cloud.test.learn.java.bytecodetools;


import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.objectweb.asm.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * Created on 2019/1/21.<br/>
 *
 * @author yudong
 */
@Slf4j
public class AsmTest {


    public static void main(String[] args) {
        Pair<String, Map<String, String>> map = handleContent("install ClothingManage success,current version:2022-12-23 12:19:47|ip:以太网 3 Ethernet 10.254.254.11|ip:WLAN Wireless80211 10.11.52.51|OS Version:Microsoft Windows NT 6.2.9200.0|OS ProcessorNameString:Intel(R) Core(TM) i7-1065G7 CPU @ 1.30GHz|OS ProductName:Windows 10 Pro|OS CurrentVersion:6.3 19044|OS ComputerName:022286101557|OS UserName:admin|OS MachineName:022286101557|Screen Device Name: \\\\.\\DISPLAY1|Screen Bounds: {X=0,Y=0,Width=2256,Height=1504}|Screen Type: System.Windows.Forms.Screen|Screen Working Area: {X=0,Y=0,Width=2256,Height=1444}|Screen Primary Screen: True|CurrentDirectory:D:\\Program Files\\ClothesManage\\ClothingManage|StartupPath:D:\\Program Files\\ClothesManage\\ClothingManage|ProcessorCount:8|WorkingSet:46698496|Is64BitOperatingSystem:True");
        System.out.println(map);
    }

    private static Pair<String, Map<String, String>> handleContent(String content) {
        String[] split = content.split("\\|");
        Map<String, String> map = Maps.newHashMap();
        for (String s : split) {
            String[] split1 = s.split(":");
            map.put(split1[0], split1[1]);
        }
        String id = map.getOrDefault("OS ComputerName", "") + "~"
                + map.getOrDefault("OS UserName", "") + "~"
                + map.getOrDefault("OS MachineName", "") + "~"
                + map.getOrDefault("CPU ID", "CPUID");
        return Pair.of(id, map);
    }

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
