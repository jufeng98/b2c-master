package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */

fun change() {
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
}
