package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */

fun getSize(array: List<String>?): Int? {
    // If not null 缩写
    return array?.size
}

fun getSize1(array: List<String>?): Int {
    // If not null and else 缩写
    return array?.size ?: 0
}

fun safety() {
    val values = mapOf("a" to 1)
    val email = values["email"] ?: throw IllegalStateException("Email is missing!")

    val emails = mutableListOf<String>() // 可能会是空集合
    val mainEmail = emails.firstOrNull() ?: ""

    val value = null

    value?.let {
        // 代码会执行到此处, 假如data不为null
    }

    val mapped = value?.let { transformValue(it) } ?: "defaultValue"
// 如果该值或其转换结果为空，那么返回 defaultValue。
}

fun transformValue(it: String?): String? {
    return it
}
