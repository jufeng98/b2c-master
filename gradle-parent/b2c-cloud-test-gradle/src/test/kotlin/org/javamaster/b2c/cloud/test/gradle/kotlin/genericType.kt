package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
class Box<T>(t: T) {
    var value = t
}

val box: Box<Int> = Box<Int>(1)
val box1 = Box(1) // 1 具有类型 Int，所以编译器知道我们说的是 Box<Int>。

fun <T> singletonList(item: T): List<T> {
    // ……
    return mutableListOf(item)
}

fun <T> T.basicToString(): String {  // 扩展函数
    // ……
    return ""
}