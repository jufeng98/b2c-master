package org.javamaster.b2c.cloud.test.gradle.kotlin

import com.google.gson.Gson
import com.google.gson.JsonElement
import java.math.BigDecimal

/**
 *
 * @author yudong
 * @date 2020/6/23
 */

// 定义函数
fun sum(a: Int, b: Int): Int {
    return a + b
}

// 将表达式作为函数体、返回值类型自动推断的函数
fun sum1(a: Int, b: Int) = a + b

// 函数返回无意义的值,Unit可省略
fun sum3(a: Int, b: Int): Unit {
    // 字符串模板
    println("$a+$b=${a + b}")
}

// 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空
fun parseInt(s: String?): Int? {
    if (s == null) {
        return -1
    }
    return s.toInt()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用 `x * y` 会导致编译错误，因为它们可能为 null
    if (x != null && y != null) {
        // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
        println(x * y)
    } else {
        println("'$arg1' or '$arg2' is not a number")
    }
}

// is 运算符检测一个表达式是否某类型的一个实例
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
    }

    // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    return null
}

fun getStringLength1(obj: Any): Int? {
    if (obj !is String) return null
    // `obj` 在这一分支自动转换为 `String`
    return obj.length
}

fun getStringLength2(obj: Any): Int? {
    // `obj` 在 `&&` 右边自动转换成 `String` 类型
    if (obj is String && obj.length > 0) {
        return obj.length
    }
    return null
}

// 函数的默认参数
fun foo(a: Int = 0, b: String = "") {
    println(b + a)
}


// 扩展函数
fun String.trimAllSpace(s: String): String {
    return s.replace(" ", "")
}

// 对于需要泛型信息的泛型函数的适宜形式
inline fun <reified T : Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)

//Kotlin 的标准库有一个 TODO() 函数，该函数总是抛出一个 NotImplementedError。 其返回类型为 Nothing，因此无论预期类型是什么都可以使用它。
fun calcTaxes(): BigDecimal = TODO("Waiting for feedback from accounting")