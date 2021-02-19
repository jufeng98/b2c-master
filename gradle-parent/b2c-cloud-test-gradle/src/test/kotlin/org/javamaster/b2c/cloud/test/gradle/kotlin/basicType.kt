package org.javamaster.b2c.cloud.test.gradle.kotlin

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
// 在 Kotlin 中，所有东西都是对象，在这个意义上讲我们可以在任何变量上调用成员函数与属性。
// 对于数字没有隐式拓宽转换
// Kotlin 提供了如下的内置类型来表示数字（与 Java 很相近）：
// Type  	Bit     width
// Double	64
// Float	32
// Long	    64
// Int	    32
// Short	16
// Byte  	8
fun baseType() {
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010

//   所有以未超出 Int 最大值的整型值初始化的变量都会推断为 Int 类型。如果初始值超过了其最大值，那么推断为 Long 类型。 如需显式指定 Long 型值，请在该值后追加 L 后缀。
    val one = 1 // Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long
    val oneByte: Byte = 1
//   对于以小数初始化的变量，编译器会推断为 Double 类型。 如需将一个值显式指定为 Float 类型，请添加 f 或 F 后缀。 如果这样的值包含多于 6～7 位十进制数，那么会将其舍入。
    val pi = 3.14 // Double
    val e = 2.7182818284 // Double
    val eFloat = 2.7182818284f // Float，实际值为 2.7182817

    var a: Int = 10000
    println(a === a) // 输出“true”
    var boxedA: Int? = a
    var anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA) // ！！！输出“false”！！！

    a = 10000
    println(a == a) // 输出“true”
    boxedA = a
    anotherBoxedA = a
    println(boxedA == anotherBoxedA) // 输出“true”

    val b: Byte = 1 // OK, 字面值是静态检测的
    val i: Int = b.toInt() // OK：显式拓宽
    print(i)

//    对于位运算，没有特殊字符来表示，而只可用中缀方式调用命名函数
    val x = (1 shl 2) and 0x000FF000
//    这是完整的位运算列表（只用于 Int 与 Long）：
//    shl(bits) – 有符号左移 (Java 的 <<)
//    shr(bits) – 有符号右移 (Java 的 >>)
//    ushr(bits) – 无符号右移 (Java 的 >>>)
//    and(bits) – 位与
//    or(bits) – 位或
//    xor(bits) – 位异或
//    inv() – 位非

}

