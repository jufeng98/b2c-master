package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
fun maxOf(a: Int, b: Int): Int {
    // 条件表达式
    return if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }
}

// 条件表达式
fun maxOf1(a: Int, b: Int) = if (a > b) a else b

fun test3() {
    val result = try {
        getSize1(null)
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
    val string = if (result == 1) {
        "one"
    } else if (result == 2) {
        "two"
    } else {
        "three"
    }
}
