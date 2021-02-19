package org.javamaster.b2c.cloud.test.gradle.kotlin

import com.sun.corba.se.impl.orbutil.graph.Graph
import org.junit.Before
import org.junit.Test
import sun.security.provider.certpath.Vertex
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator
import javax.inject.Inject
import javax.swing.JButton
import javax.swing.JFrame
import kotlin.properties.Delegates
import kotlin.reflect.KProperty
import org.javamaster.b2c.cloud.test.gradle.kotlin.BeanNameGenerator as BeanNameGenerator1

fun main(args: Array<String>) {
    sum3(3, 4)
    foo()
    copyAddress(Address())
}

fun test6() {
    var person = Person1("liang")
}


fun Int.max(a: Int): Int {
    var employee = Employee("liang", "yudong")
    var employee1 = employee.copy(firstName = "li")
    var (first, second) = employee
    println("$first $second")
    var pair = Pair("yudong", 1)
    return if (a > this) a else this
}


fun annoymousFunc() {
    var frame = JFrame("")
    frame.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            println(e)
        }
    })
    var button = JButton("")
    button.addActionListener { print(it) }
}

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}

open class AB(x: Int) {
    public open val y: Int = x
}

interface BC {}

fun expression() {
    val ab: AB = object : AB(1), BC {
        override val y = 15
    }
}

fun foo12() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

class CD {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题
        //val x2 = publicFoo().x  // 错误：未能解析的引用“x”
    }
}

object ServiceImpl {
    var Named = "liang yudong"
}

class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

class MyClass1 {
    companion object {}
}

class MyClass2 {
    companion object Named {}
}

class MyClass3 {
    companion object {}
}

fun companionObj() {
    val instance = MyClass.create()
    val x = MyClass1
    val y = MyClass2
    val z = MyClass3
}

class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun delegate() {
    val e = Example()
    println(e.p)
    e.p = "new value"
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }
    println(lazyValue)
    println(lazyValue)

    val user = User()
    user.name = "first"
    user.name = "second"

    val user1 = User1(mapOf(
            "name" to "John Doe",
            "age" to 25
    ))
    user1.age
}

class User {
    var name: String by Delegates.observable("<no name>") { prop, old, new ->
        println("$old -> $new")
    }
}

class User1(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

open class AA {
    open fun foo(i: Int = 10) {}
}

class BB : AA() {
    override fun foo(i: Int) {}  // 不能有默认值
}

fun foo(bar: Int = 0, baz: Int) {}
fun foo1(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {}
fun reformat(str: String,
             normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') {
}

fun foo(vararg strings: String) {}
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

fun func() {
    foo(baz = 1) // 使用默认值 bar = 0
    foo1(1) { println("hello") }     // 使用默认值 baz = 1
    foo1(qux = { println("hello") }) // 使用两个默认值 bar = 0 与 baz = 1
    foo1 { println("hello") }        // 使用两个默认值 bar = 0 与 baz = 1
    reformat("")
    reformat("",
            normalizeCase = true,
            upperCaseFirstLetter = true,
            divideByCamelHumps = false,
            wordSeparator = '_'
    )
    reformat("", wordSeparator = '_')
    foo(strings = *arrayOf("a", "b", "c"))
    val list = asList(1, 2, 3)
}

fun double(x: Int): Int = x * 2

infix fun Int.shll(x: Int): Int {
    return x
}

fun infixx() {
    // 用中缀表示法调用该函数
    1 shll 2

// 等同于这样
    1.shll(2)
}

class MyStringCollection {
    infix fun add(s: String) {}

    fun build() {
        this add "abc"   // 正确
        add("abc")       // 正确
        // add "abc"        // 错误：必须指定接收者
    }
}

fun dfs(graph: Graph?) {
    val visited = HashSet<Vertex>()
    fun dfs(current: Vertex) {
        if (!visited.add(current)) return
//        for (v in current.neighbors)
        dfs(null)
    }

//    dfs(graph.vertices[0])
}

fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun call() {
    val items = listOf(1, 2, 3, 4, 5)

// Lambdas 表达式是花括号括起来的代码块。
    items.fold(0, {
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
        acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    })

// lambda 表达式的参数类型是可选的，如果能够推断出来的话：
    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })

// 函数引用也可以用于高阶函数调用：
    val product = items.fold(1, Int::times)

    var fun0 = { a: Int, b: Int -> a + b }
    var fun1 = fun(s: String): Int { return s.toIntOrNull() ?: 0 }

    var map = mapOf<String, Int>()
    map.forEach { _, value -> println("$value!") }

    map = mapOf("key1" to 1, "key2" to 2)

    val numbersMap = mutableMapOf<String, String>().apply { this["one"] = "1"; this["two"] = "2" }

    var num1 = listOf(1, 3, 5, 7, 9)
    var num2 = mutableListOf<Int>()
    num1.filterTo(num2) { it > 5 }
}