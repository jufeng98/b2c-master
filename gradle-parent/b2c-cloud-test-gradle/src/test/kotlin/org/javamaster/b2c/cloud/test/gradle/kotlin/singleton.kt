package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
object Resource {
    val name = "Name"
    // 编译期常量
    const val NAME = "Name"
}

fun singleton() {
    println(Resource.name)
}
