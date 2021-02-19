/**
 * Created by yudong on 2017/6/15 0015.
 * String 类型用于表示由零或多个 16 位 Unicode 字符组成的字符序列，即字符串。字符串可以由双
 * 引号（ "）或单引号（ '）表示
 \n 换行
 \t 制表
 \b 空格
 \r 回车
 \f 进纸
 \\ 斜杠
 \' 单引号（ ' ），在用单引号表示的字符串中使用。例如： 'He said, \'hey.\''
 \" 双引号（ " ），在用双引号表示的字符串中使用。例如： "He said, \"hey.\""
 \xnn 以十六进制代码nn表示的一个字符（其中n为0～ F）。例如， \x41表示"A"
 \unnnn 以十六进制代码nnnn表示的一个Unicode字符（其中n为0～ F）。例如， \u03a3表示希腊字符Σ
 要把一个值转换为一个字符串有两种方式。第一种是使用几乎每个值都有的 toString() 方法,
 但 null 和 undefined 值没有这个方法。
 在不知道要转换的值是不是 null 或 undefined 的情况下，还可以使用转型函数 String() ，这个
 函数能够将任何类型的值转换为字符串。
 String() 函数遵循下列转换规则：
  如果值有 toString() 方法，则调用该方法（没有参数）并返回相应的结果；
  如果值是 null，则返回"null" ；
  如果值是 undefined，则返回"undefined" 。
 String 类型的每个实例都有一个 length 属性，表示字符串中包含多个字符。
 两个用于访问字符串中特定字符的方法是： charAt() 和 charCodeAt() 。
 match() ，在字符串上调用这个方法，本质上与调用 RegExp 的 exec() 方法相同。 match() 方法只接受一个参数，要么是一
 个正则表达式，要么是一个 RegExp 对象。
 search() 。这个方法的唯一参数与 match() 方法的参数相同：由字
 符串或 RegExp 对象指定的一个正则表达式。 search() 方法返回字符串中第一个匹配项的索引；如果没
 有找到匹配项，则返回-1。
 replace() 方法。这个方法接受两个参数：第
 一个参数可以是一个 RegExp 对象或者一个字符串（这个字符串不会被转换成正则表达式），第二个参
 数可以是一个字符串或者一个函数。如果第一个参数是字符串，那么只会替换第一个子字符串。要想替
 换所有子字符串，唯一的办法就是提供一个正则表达式，而且要指定全局（ g）标志
 如果第二个参数是字符串，那么还可以使用一些特殊的字符序列，将正则表达式操作得到的值插入
 到结果字符串中。下表列出了 ECMAScript 提供的这些特殊的字符序列。
 $$       $
 $&       匹配整个模式的子字符串。与RegExp.lastMatch的值相同
 $'       匹配的子字符串之前的子字符串。与RegExp.leftContext的值相同
 $`       匹配的子字符串之后的子字符串。与RegExp.rightContext的值相同
 $n       匹配第n个捕获组的子字符串，其中n等于0～ 9。例如， $1是匹配第一个捕获组的子字符串， $2是匹配第
 二个捕获组的子字符串，以此类推。如果正则表达式中没有定义捕获组，则使用空字符串
 $nn      匹配第nn个捕获组的子字符串，其中nn等于01～ 99。例如， $01是匹配第一个捕获组的子字符串， $02
 是匹配第二个捕获组的子字符串，以此类推。如果正则表达式中没有定义捕获组，则使用空字符串
 replace() 方法的第二个参数也可以是一个函数。在只有一个匹配项（即与模式匹配的字符串）的
 情况下，会向这个函数传递 3 个参数：模式的匹配项、模式匹配项在字符串中的位置和原始字符串。
 最后一个与模式匹配有关的方法是 split() ，这个方法可以基于指定的分隔符将一个字符串分割成
 多个子字符串，并将结果放在一个数组中。分隔符可以是字符串，也可以是一个 RegExp 对象（这个方
 法不会将字符串看成正则表达式）。 split() 方法可以接受可选的第二个参数，用于指定数组的大小，
 以便确保返回的数组不会超过既定大小。
 . localeCompare() 方法
 与操作字符串有关的最后一个方法是 localeCompare() ，这个方法比较两个字符串，并返回下列
 值中的一个：
  如果字符串在字母表中应该排在字符串参数之前，则返回一个负数（大多数情况下是-1，具体
 的值要视实现而定）；
  如果字符串等于字符串参数，则返回 0；
  如果字符串在字母表中应该排在字符串参数之后，则返回一个正数（大多数情况下是 1，具体的
 值同样要视实现而定）。
 String 构造函数本身还有一个静态方法： fromCharCode() 。这个方法的任务是接收一或
 多个字符编码，然后将它们转换成一个字符串。从本质上来看，这个方法与实例方法 charCodeAt()
 执行的是相反的操作。
 */
var a = 22;
var b = true;
console.log(a.toString());
console.log(a.toString(2));
console.log(a.toString(16));
console.log(b.toString());
console.log(String(null));
console.log(String(undefined).length);

var text = "cat, bat, sat, fat";
var pattern = /.at/;
//与 pattern.exec(text) 相同
var matches = text.match(pattern);
console.log(matches.index); //0
console.log(matches[0]); //"cat"
console.log(pattern.lastIndex); //0

var text = "cat, bat, sat, fat";
var pos = text.search(/at/);
console.log(pos); //1

var text = "cat, bat, sat, fat";
var result = text.replace("at", "ond");
console.log(result); //"cond, bat, sat, fat"
result = text.replace(/at/g, "ond");
console.log(result); //"cond, bond, sond, fond"


var text = "cat, bat, sat, fat";
result = text.replace(/(.at)/g, "word ($1)");
console.log(result); //word (cat), word (bat), word (sat), word (fat)

function htmlEscape(text) {
    return text.replace(/[<>"&]/g, function (match, pos, originalText) {
        switch (match) {
            case "<":
                return "&lt;";
            case ">":
                return "&gt;";
            case "&":
                return "&amp;";
            case "\"":
                return "&quot;";
        }
    });
}

console.log(htmlEscape("<p class=\"greeting\">Hello world!</p>"));

var colorText = "red,blue,green,yellow";
var colors1 = colorText.split(","); //["red", "blue", "green", "yellow"]
var colors2 = colorText.split(",", 2); //["red", "blue"]
var colors3 = colorText.split(/[^\,]+/); //["", ",", ",", ",", ""]