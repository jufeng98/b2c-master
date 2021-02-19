package com.javamaster.b2c.cloud.test.learn.java.virtualmachine;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:PermSize=10m -XX:MaxPermSize=10m -XX:+PrintGCDetails
 *
 * @author yudong
 * @date 2018/6/14
 */
public class HeapOOMTest {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}

/**
 * VM Args:-XX:PermSize=2m -XX:MaxPermSize=2m
 */
class SampleClassTest {

    public void test() {
    }

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(SampleClassTest.class);
            enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
                Object result = proxy.invokeSuper(obj, args1);
                return result;
            });
            SampleClassTest sample = (SampleClassTest) enhancer.create();
            sample.test();
        }
    }
}

/**
 * VM Args:-Xss128k
 */
class StackOverFlow {
    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.stack();
        } catch (Throwable throwable) {
            System.out.println(stackOverFlow.length);
            throwable.printStackTrace();
        }
    }

    private int length = 0;

    private void stack() {
        length++;
        stack();
    }
}

/**
 * VM Args:-Xmx20m -XX:MaxDirectMemorySize=10m
 */
class DirectMemoryOOMTest {
    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024);
        }
    }
}

/**
 * VM Args:-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
 * <p>
 * 停顿类型 GC区域   GC前已使用容量->GC后已使用容量（总容量） GC前Java堆已使用容量->GC后Java堆已使用容量（Java堆总容量）GC耗时 用户态消耗的CPU时间 内核态消耗的CPU时间 操作从开始到结束所经过的墙钟时间
 * [GC minor GC     [DefNew serial收集器新生代
 * [Full GC stop the world [ParNew ParNew收集器新生代
 * [Full GC（System） System.gc()方法触发 [PSYoungGen Parallel Scavenge收集器新生代
 * [GC [PSYoungGen: 6859K->1012K(9216K)] 6859K->3526K(19456K), 0.0015852 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC [PSYoungGen: 7363K->1012K(9216K)] 9877K->9782K(19456K), 0.0035625 secs] [Times: user=0.09 sys=0.00, real=0.00 secs]
 * [Full GC [PSYoungGen: 1012K->0K(9216K)] [ParOldGen: 8769K->7332K(10240K)] 9782K->7332K(19456K) [PSPermGen: 2986K->2985K(21504K)], 0.0128259 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 */
class HandlePromotionTest {
    public static void main(String[] args) {
        int oneMb = 1024 * 1024;
        byte[] allocation1,
                allocation2,
                allocation3,
                allocation4,
                allocation5,
                allocation6,
                allocation7;
        allocation1 = new byte[2 * oneMb];
        allocation2 = new byte[2 * oneMb];
        allocation3 = new byte[2 * oneMb];
        allocation1 = null;
        allocation4 = new byte[2 * oneMb];
        allocation5 = new byte[2 * oneMb];
        allocation6 = new byte[2 * oneMb];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * oneMb];
    }
}