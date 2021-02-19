package org.javamaster.b2c.cloud.test.gradle.kotlin

import javax.inject.Inject

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
//创建 DTOs（POJOs/POCOs）
//会为 Customer 类提供以下功能：
//所有属性的 getters （对于 var 定义的还有 setters）
//equals()
//hashCode()
//toString()
//copy()
//所有属性的 component1()、 component2()……等等
data class Person(val name: String = "", var age: Int = 12) {
}

var person = Person("liangyudong", 28)

val myRectangle = Person().apply {
    age = 22
}

// // 从 Any 隐式继承,Any 并不是 java.lang.Object；尤其是，它除了 equals()、hashCode() 与 toString() 外没有任何成员。
class Invoice {}

// 类没有类体，可以省略花括号
class emptyCalss

class Person1 constructor(firstName: String) {
    val firstName = firstName
    fun output(): String {
        return this.firstName
    }
}

// 主构造函数
class Person2 @Inject constructor(private val firstName: String) {
    // 次构造函数
    constructor(firstName: String, secondName: String) : this(firstName)

    fun output(): String {
        return this.firstName
    }
}

// 如果主构造函数的所有的参数都有默认值，编译器会生成 一个额外的无参构造函数，它将使用默认值。
// 这使得 Kotlin 更易于使用像 Jackson 或者 JPA 这样的通过无参构造函数创建类的实例的库。
// 主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
class Person3(private val firstName: String, private var secondName: String) {
    private var age: Int = 0

    // 次构造函数
    constructor(firstName: String, secondName: String, age: Int) : this(firstName, secondName) {
        this.age = age
    }

    // 主构造函数不能包含任何的代码,初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中。
    // 初始化块中的代码实际上会成为主构造函数的一部分,因此所有初始化块中的代码都会在次构造函数体之前执行。
    init {
        this.secondName = this.secondName.toUpperCase()
    }

    init {
        println("Second initializer block that prints ${secondName.length}")
    }

    fun output(): String {
        return this.firstName
    }
}

open class Shape {
    open val borderColor: String = ""

    open fun draw() { /*……*/
    }

    fun fill() { /*……*/
    }
}

class Circle() : Shape() {
    override fun draw() { /*……*/
    }
}

open class Rectangle() : Shape() {
    final override fun draw() { /*……*/
    }
}

// 在一个内部类中访问外部类的超类，可以通过由外部类名限定的 super 关键字来实现
class FilledRectangle : Shape() {
    override fun draw() { /* …… */
    }

    override val borderColor: String get() = "black"

    inner class Filler {
        fun fill() { /* …… */
        }

        fun drawAndFill() {
            super@FilledRectangle.draw() // 调用 Rectangle 的 draw() 实现
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle.borderColor}") // 使用 Rectangle 所实现的 borderColor 的 get()
        }
    }
}

open class Base(name: Int) {
    open fun v() {}
    fun nv() {}
}

class Derived(name: Int, age: Int) : Base(name) {
    override fun v() {}
    // 标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用 final 关键字
    // final override fun v() { }
}

open class Foo {
    open val x: Int
        get() {
            return x
        }
}

class Bar1 : Foo() {
    override var x: Int = 2
        get() {
            return -x
        }
}

open class Foo1 {
    open fun f() {
        println("Foo.f()")
    }

    open val x: Int get() = 1
}

class Bar : Foo1() {
    override fun f() {
        super.f()
        println("Bar.f()")
    }

    override val x: Int get() = super.x + 1
}

class Bar2 : Foo1() {
    override fun f() { /* …… */
    }

    override val x: Int get() = 0

    inner class Baz2 {
        fun g() {
            super@Bar2.f() // 调用 Foo1 实现的 f()
            println(super@Bar2.x) // 使用 Foo1 实现的 x 的 getter
            this@Bar2.f() // 调用 Bar1 实现的 f()
        }
    }
}

open class A {
    open fun f() {
        print("A")
    }

    fun a() {
        print("a")
    }
}

interface B {
    fun f() {
        print("B")
    } // 接口成员默认就是“open”的

    fun b() {
        print("b")
    }
}

class C() : A(), B {
    // 编译器要求覆盖 f()：
    override fun f() {
        super<A>.f() // 调用 A.f()
        super<B>.f() // 调用 B.f()
    }
}

open class Base1 {
    open fun f() {}
}

abstract class Derived1 : Base1() {
    abstract override fun f()
}

interface BeanNameGenerator {
    fun generate(className: String): String
    fun generete(clz: Class<Any>): String {
        return clz.name
    }
}

class GeneratorImpl : BeanNameGenerator {
    override fun generate(className: String): String {
        return className.toUpperCase()
    }
}

interface Named {
    val name: String
}

interface Person4 : Named {
    var firstName: String
    var lastName: String
    override val name: String
        get() = "$firstName $lastName"
}

data class Employee(override var firstName: String, override var lastName: String) : Person4 {
}

//密封类用来表示受限的类继承结构：当一个值为有限几种的类型、而不能有任何其他类型时。在某种意义上，
// 他们是枚举类的扩展：枚举类型的值集合也是受限的，但每个枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例
sealed class Expr

data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
    // 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}