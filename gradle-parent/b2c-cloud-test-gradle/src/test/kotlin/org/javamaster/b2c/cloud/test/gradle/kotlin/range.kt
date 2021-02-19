package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
// 使用区间（range）
fun range() {
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits in range")
    }

    val list = listOf("a", "b", "c")
    //    检测元素是否存在于或不存在于集合中
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }

    for (x in 1..10 step 2) {
        print(x)
    }

    for (x in 9 downTo 0 step 3) {
        print(x)
    }

    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    for ((index, value) in fruits.withIndex()) {
        println("the element at $index is $value")
    }
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }
}
