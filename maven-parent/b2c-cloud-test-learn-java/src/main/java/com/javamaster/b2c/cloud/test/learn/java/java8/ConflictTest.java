package com.javamaster.b2c.cloud.test.learn.java.java8;

/**
 * 解决冲突的机制
 * <ol>
 * <li>首先，类或父类中显式声明的方法，其优先级高于所有的默认方法。</li>
 * <li>如果用第一条无法判断，方法签名又没有区别，那么选择提供最具体实现的默认方法的接口。</li>
 * <li>最后，如果冲突依旧无法解决，你就只能在你的类中覆盖该默认方法，显式地指定在你的类中使用哪一个接口中的方法。</li>
 * </ol>
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public class ConflictTest {
    public static void main(String[] args) {
        new C().hello();
        new E().hello();
        new H().hello();
        new K().hello();
        new N().hello();
    }
}

interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}

interface F {
    default void hello() {
        System.out.println("Hello from F");
    }
}

interface B extends A {
    @Override
    default void hello() {
        System.out.println("Hello from B");
    }
}

class C implements B, A {
}

class D implements A {
}

class E extends D implements B, A {
}

class H implements A, F {

    @Override
    public void hello() {
        A.super.hello();
    }
}

interface I extends A {
}

interface J extends A {
}

class K implements I, J {
}

interface L extends A {
}

interface M extends F {
}

class N implements L, M {

    @Override
    public void hello() {
        M.super.hello();
    }
}