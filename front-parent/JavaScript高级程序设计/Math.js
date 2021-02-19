/**
 * Created by yudong on 2017/6/16 0016.

 ECMAScript 还为保存数学公式和信息提供了一个公共位置，即 Math 对象。与我们在 JavaScript 直接编写的计算功能相比， Math 对象提供
 的计算功能执行起来要快得多。 Math 对象中还提供了辅助完成这些计算的属性和方法。
 Math.E              自然对数的底数，即常量e的值
 Math.LN10           10的自然对数
 Math.LN2            2的自然对数
 Math.LOG2E          以2为底e的对数
 Math.LOG10          E 以10为底e的对数
 Math.PI            π的值
 Math.SQRT1_2        1/2的平方根（即2的平方根的倒数）
 Math.SQRT2          2的平方根
 方法                                  方法说明
 Math.abs(num)         返回num 的绝对值 Math.asin(x) 返回x的反正弦值
 Math.exp(num)         返回Math.E的num 次幂 Math.atan(x) 返回x的反正切值
 Math.log(num)         返回num 的自然对数 Math.atan2(y,x) 返回y/x的反正切值
 Math.pow(num, power)  返回num 的power次幂 Math.cos(x) 返回x的余弦值
 Math.sqrt(num)        返回num 的平方根 Math.sin(x) 返回x的正弦值
 Math.acos(x)          返回x的反余弦值 Math.tan(x) 返回x的正切值
 */
var max = Math.max(3, 54, 32, 16);
console.log(max); //54
var min = Math.min(3, 54, 32, 16);
console.log(min); //3
var values = [1, 2, 3, 4, 5, 6, 7, 8];
var max = Math.max.apply(Math, values);
console.log(Math.ceil(25.9)); //26
console.log(Math.ceil(25.5)); //26
console.log(Math.ceil(25.1)); //26
console.log(Math.round(25.9)); //26
console.log(Math.round(25.5)); //26
console.log(Math.round(25.1)); //25
console.log(Math.floor(25.9)); //25
console.log(Math.floor(25.5)); //25
console.log(Math.floor(25.1)); //25
var num = Math.floor(Math.random() * 10 + 1);
console.log(num)