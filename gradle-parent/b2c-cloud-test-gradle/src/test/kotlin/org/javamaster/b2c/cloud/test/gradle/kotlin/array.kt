package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */

fun array() {
    val array = arrayOf(1, 2, 3)
    array.forEach { println(it) }

    // 创建一个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
    val asc = Array(5) { i -> (i * i).toString() }
    asc.forEach { println(it) }

    // 原生类型数组
    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]

    // 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
    val arr = IntArray(5)

// 例如：用常量初始化数组中的值
// 大小为 5、值为 [42, 42, 42, 42, 42] 的整型数组
    val arr1 = IntArray(5) { 42 }

// 例如：使用 lambda 表达式初始化数组中的值
// 大小为 5、值为 [0, 1, 2, 3, 4] 的整型数组（值初始化为其索引值）
    var arr2 = IntArray(5) { it * 1 }
}
