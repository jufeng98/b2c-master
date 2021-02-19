package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
fun variable() {
    val a: Int = 1  // 立即赋值
    val b = 2   // 自动推断出 `Int` 类型
    val c: Int  // 如果没有初始值类型不能省略
    c = 3       // 明确赋值

    var x = 5 // 自动推断出 `Int` 类型
    x += 1
}

// 顶层变量
val PI = 3.14
var x = 0

fun incrementX() {
    x += 1
    // 延迟属性
    val p: String by lazy {
        // 计算该字符串
        "hello world"
    }
    println(p)
}