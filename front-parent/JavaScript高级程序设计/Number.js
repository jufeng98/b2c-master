/**
 * Created by yudong on 2017/6/14 0014.

 * 八进制字面值的第一位必须是零（ 0），然后是八进制数字序列（ 0～ 7）。如果字面值中的数值超出了范围，那么前导零将被忽略，后面的
 * 数值将被当作十进制数值解析。
 * 十六进制字面值的前两位必须是 0x，后跟任何十六进制数字（ 0～ 9 及 A～ F）。其中，字母 A～ F可以大写，也可以小写。
 * 在进行算术计算时，所有以八进制和十六进制表示的数值最终都将被转换成十进制数值
 * 有 3 个函数可以把非数值转换为数值： Number() 、 parseInt() 和 parseFloat() 。第一个函数，即转型函数 Number() 可以用于任何
 * 数据类型，而另两个函数则专门用于把字符串转换成数值。Number() 函数的转换规则如下。
  如果是 Boolean 值， true 和 false 将分别被转换为 1 和 0。
  如果是数字值，只是简单的传入和返回。
  如果是 null 值，返回 0。
  如果是 undefined，返回 NaN。
  如果是字符串，遵循下列规则：
  如果字符串中只包含数字（包括前面带正号或负号的情况），则将其转换为十进制数值，即"1"会变成 1， "123" 会变成 123，而"011" 会变
 成 11（注意：前导的零被忽略了）；
  如果字符串中包含有效的浮点格式，如"1.1" ，则将其转换为对应的浮点数值（同样，也会忽略前导零）；
  如果字符串中包含有效的十六进制格式，例如"0xf" ，则将其转换为相同大小的十进制整数值；
  如果字符串是空的（不包含任何字符），则将其转换为 0；
  如果字符串中包含除上述格式之外的字符，则将其转换为 NaN。
  如果是对象，则调用对象的 valueOf() 方法，然后依照前面的规则转换返回的值。如果转换的结果是 NaN，则调用对象的 toString() 方法，
 然后再次依照前面的规则转换返回的字符串值。
 parseInt() 函数在转换字符串时，更多的是看其是否符合数值模式。它会忽略字符串前面的空格，直至找到第一个非空格字符。如果第一个字符
 不是数字字符或者负号， parseInt()就会返回 NaN；也就是说，用 parseInt() 转换空字符串会返回 NaN（ Number() 对空字符返回 0）。如
 果第一个字符是数字字符， parseInt() 会继续解析第二个字符，直到解析完所有后续字符或者遇到了一个非数字字符。例如， "1234blue" 会
 被转换为 1234，因为"blue" 会被完全忽略。类似地， "22.5"会被转换为 22，因为小数点并不是有效的数字字符。
 如果字符串中的第一个字符是数字字符， parseInt() 也能够识别出各种整数格式（即前面讨论的十进制、八进制和十六进制数）。也就是说，
 如果字符串以"0x" 开头且后跟数字字符，就会将其当作一个十六进制整数；如果字符串以"0" 开头且后跟数字字符，则会将其当作一个八进制数
 来解析。可以为这个函数提供第二个参数：转换时使用的基数（即多少进制）。
 toFixed() 方法会按照指定的小数位返回数值的字符串表示
 toExponential() ，该方法返回以指数表示法（也称 e 表示法）表示的数值的字符串形式。与 toFixed() 一样， toExponential() 也接收一
 个参数，而且该参数同样也是指定输出结果中的小数位数。
 toPrecision() 方法可能会返回固定大小（ fixed）格式，也可能返回指数（ exponential）格式；具体规则是看哪种格式最合适。这个方法
 接收一个参数，即表示数值的所有数字的位数（不包括指数部分）。
 */
console.log(032);
console.log(0o32);
console.log(0x32);
console.log(0.1234);
console.log(0.1234e5);
console.log(10.0);
console.log(0 / 0);
console.log(-1 / 0);
console.log(1 / 0);
console.log(Number.MIN_VALUE);
console.log(Number.MAX_VALUE);
console.log(Number.NaN);
console.log(Number.NEGATIVE_INFINITY);
console.log(Number.POSITIVE_INFINITY);
console.log(NaN == NaN);
console.log(isNaN(NaN));
var num = 10.005;
console.log(num.toFixed(2));
console.log(num.toExponential(1));
console.log(num.toPrecision(1)); //"1e+2"
console.log(num.toPrecision(2)); //"10"
console.log(num.toPrecision(3)); //"10.0"