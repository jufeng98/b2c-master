package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
// 使用 when 表达式
fun describe(a: Any): String {
    val fruits = listOf("apple", "banana", "orange")
    when {
        "orange" in fruits -> println("juicy")
        "apple" in fruits -> println("apple is fine too")
    }
    when (a) {
        // 很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔
        0, 1 -> print("x == 0 or x == 1")
        a == "b" -> print("s encodes x")
        is String -> a.toUpperCase()
        else -> print("otherwise")
    }
    return when (a) {
        1 -> "one"
        "hello" -> "greeting"
        is Long -> "long"
        in 10..20 -> "between 10 to 20"
        !is String -> "not string"
        else -> "unknown"
    }
}

fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}
