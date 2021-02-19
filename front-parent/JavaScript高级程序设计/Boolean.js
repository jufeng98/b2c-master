/**
 * Created by yudong on 2017/6/14 0014.
 */
/**
 * 数据类型            转换为true的值            转换为false的值
 * Boolean                 true                      false
 * String             任何非空字符串              "" （空字符串）
 * Number        任何非零数字值（包括无穷大） 0和NaN（参见本章后面有关NaN的内容）
 * Object                任何对象                     null
 * Undefined               n/a                      undefined
 *
 * 使用两个!!相当于使用Boolean()函数.永远不要使用 Boolean 对象
 */
console.log(Boolean(""));
console.log(!!"");
console.log(Boolean(0));
console.log(Boolean(null));
console.log(Boolean(undefined));
console.log(Boolean(NaN));
