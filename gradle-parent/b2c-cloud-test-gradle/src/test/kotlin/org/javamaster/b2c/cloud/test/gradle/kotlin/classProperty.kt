package org.javamaster.b2c.cloud.test.gradle.kotlin

import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 *
 * @author yudong
 * @date 2020/6/23
 */

class Address {
    var name: String = ""
        // 定义了一个自定义的 setter，那么每次给属性赋值时都会调用它
        set(value) {
            field = value.toUpperCase()
        }
    var street: String = ""
    var city: String = ""
        @Inject set // 用 Inject 注解此 setter
    var state: String? = null
        private set // 此 setter 是私有的并且有默认实现
    var zip: String = ""
    val isEmpty: Boolean
        get() = this.name == ""

}

fun copyAddress(address: Address): Address {
    val result = Address() // Kotlin 中没有“new”关键字
    result.name = address.name // 将调用访问器
    result.street = address.street
    result.name = "liangyudong"
    println(result.name)
    return result
}

class MyTest {
    lateinit var subject: Address

    @Before
    fun setup() {
        subject = Address()
    }

    @Test
    fun test() {
        println(subject.city)
    }
}