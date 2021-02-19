package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
//在 Kotlin 中有这四个可见性修饰符：private、 protected、 internal 和 public。 如果没有显式指定修饰符的话，默认可见性是 public

private fun foo11() {
} // 在 example.kt 内可见

public var bar: Int = 5 // 该属性随处可见
    private set         // setter 只在 example.kt 内可见

internal val baz = 6    // 相同模块内可见

//对于类内部声明的成员：
//
//private 意味着只在这个类内部（包含其所有成员）可见；
//protected—— 和 private一样 + 在子类中可见。
//internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员；
//public —— 能见到类声明的任何客户端都可见其 public 成员。
//请注意在 Kotlin 中，外部类不能访问内部类的 private 成员。
//
//如果你覆盖一个 protected 成员并且没有显式指定其可见性，该成员还会是 protected 可见性
open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  // 默认 public

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a 不可见
    // b、c、d 可见
    // Nested 和 e 可见

    override val b = 5   // “b”为 protected
}

class Unrelated(o: Outer) {
    // o.a、o.b 不可见
    // o.c 和 o.d 可见（相同模块）
    // Outer.Nested 不可见，Nested::e 也不可见
}