package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */

// 使用 for 循环和 while 循环
fun test1() {
    val fruits = listOf("apple", "banana", "orange")
    for (fruit in fruits) {
        print("$fruit ")
    }

    for (index in fruits.indices) {
        println("item at $index is ${fruits[index]}")
    }

    var index = 0
    while (index < fruits.size) {
        println("item at $index is ${fruits[index]}")
        index++
    }

    for (x in 1..5) {
        print(x)
    }

    for (x in 1..10 step 2) {
        print(x)
    }
    println()

    for (x in 9 downTo 0 step 3) {
        print(x)
    }

// 闭区间：包含 100
    for (i in 1..100) {
    }
// 半开区间：不包含 100
    for (i in 1 until 100) {
    }
    for (x in 2..10 step 2) {
    }
    for (x in 10 downTo 1) {
    }
    if (x in 1..10) {
    }
}

