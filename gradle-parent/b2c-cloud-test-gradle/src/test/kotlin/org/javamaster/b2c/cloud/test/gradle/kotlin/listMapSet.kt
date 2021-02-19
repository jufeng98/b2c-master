package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
fun test() {
    // 只读map
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    for ((k, v) in map) {
        println("$k -> $v")
    }


    var map1 = mutableMapOf("a" to 1, "b" to 2, "c" to 3)
    for ((k, v) in map) {
        println("$k -> $v")
    }
    println(map1["key"])
    map1["key"] = 33

    // 只读list
    val list = listOf("a", "b", "c")
    list.forEach {
        println(it)
    }

    val set = setOf("a", "b", "c")
    for (s in set) {
        println(s)
    }
}