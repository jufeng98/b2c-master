/**
 * Created by yudong on 2017/6/16 0016.

 var expression = / pattern / flags ;
 其中的模式（ pattern）部分可以是任何简单或复杂的正则表达式，可以包含字符类、限定符、分组、向前查找以及反向引用。每个正则表达式
 都可带有一或多个标志（ flags），用以标明正则表达式的行为。正则表达式的匹配模式支持下列 3 个标志:
  g：表示全局（ global）模式，即模式将被应用于所有字符串，而非在发现第一个匹配项时立即停止；
  i：表示不区分大小写（ case-insensitive）模式，即在确定匹配项时忽略模式与字符串的大小写；
  m：表示多行（ multiline）模式，即在到达一行文本末尾时还会继续查找下一行中是否存在与模式匹配的项。
 与其他语言中的正则表达式类似，模式中使用的所有元字符都必须转义。正则表达式中的元字符包括：
 ( [ { \ ^ $ | ) ? * + .]}
 另一种创建正则表达式的方式是使用RegExp 构造函数，它接收两个参数：一个是要匹配的字符串模式，另一个是可选的标志字符串。
 字面量模式                            等价的字符串
 /\[bc\]at/                            "\\[bc\\]at"
 /\.at/                                "\\.at"
 /name\/age/                           "name\\/age"
 /\d.\d{1,2}/                          "\\d.\\d{1,2}"
 /\w\\hello\\123/                      "\\w\\\\hello\\\\123"
 在 ECMAScript 3 中，正则表达式字面量始终会共享同一个 RegExp 实例，而使用构造函数创建的每一个新 RegExp 实例都是一个新实例。
 ECMAScript 5 明确规定，使用正则表达式字面量必须像直接调用 RegExp 构造函数一样，每次都创建新的 RegExp 实例。
 RegExp实例方法
 RegExp 对象的主要方法是 exec() ，该方法是专门为捕获组而设计的。 exec() 接受一个参数，即要应用模式的字符串，然后返回包含第一个
 匹配项信息的数组；或者在没有匹配项的情况下返回 null。返回的数组虽然是 Array 的实例，但包含两个额外的属性： index 和 input。其
 中， index 表示匹配项在字符串中的位置，而 input 表示应用正则表达式的字符串。
 对于 exec() 方法而言，即使在模式中设置了全局标志（ g），它每次也只会返回一个匹配项。在不设置全局标志的情况下，在同一个字符串上
 多次调用 exec() 将始终返回第一个匹配项的信息。而在设置全局标志的情况下，每次调用 exec() 则都会在字符串中继续查找新匹配项。
 正则表达式的第二个方法是 test() ，它接受一个字符串参数。在模式与该参数匹配的情况下返回true；否则，返回 false。在只想知道目标字
 符串与某个模式是否匹配，但不需要知道其文本内容的情况下，使用这个方法非常方便。
 RegExp 实例继承的 toLocaleString() 和 toString() 方法都会返回正则表达式的字面量，与创建正则表达式的方式无关。正则表达式的
 valueOf() 方法返回正则表达式本身。
 */
/*
 * 匹配字符串中所有"at" 的实例
 */
var pattern1 = /at/g;
/*
 * 匹配第一个"bat" 或"cat" ，不区分大小写
 */
var pattern2 = /[bc]at/i;
/*
 * 匹配所有以 "at" 结尾的 3 个字符的组合，不区分大小写
 */
var pattern3 = /.at/gi;
/*
 * 匹配所有".at" ，不区分大小写
 */
var pattern4 = /\.at/gi;
/*
 * 匹配第一个"bat" 或"cat" ，不区分大小写
 */
var pattern1 = /[bc]at/i;
/*
 * 与 pattern1 相同，只不过是使用构造函数创建的
 */
var pattern2 = new RegExp("[bc]at", "i");

var text = "mom and dad and baby";
var pattern = /mom( and dad( and baby)?)?/gi;
var matches = pattern.exec(text);
console.log(matches.index); // 0
console.log(matches.input); // "mom and dad and baby"
console.log(matches[0]); // "mom and dad and baby"
console.log(matches[1]); // " and dad and baby"
console.log(matches[2]); // " and baby"

var text = "cat, bat, sat, fat";
var pattern1 = /.at/;
var matches = pattern1.exec(text);
console.log(matches.index); //0
console.log(matches[0]); //cat
console.log(pattern1.lastIndex); //0
matches = pattern1.exec(text);
console.log(matches.index); //0
console.log(matches[0]); //cat
console.log(pattern1.lastIndex); //0
var pattern2 = /.at/g;
var matches = pattern2.exec(text);
console.log(matches.index); //0
console.log(matches[0]); //cat
console.log(pattern2.lastIndex); //3
matches = pattern2.exec(text);
console.log(matches.index); //5
console.log(matches[0]); //bat
console.log(pattern2.lastIndex); //8
var text = "000-00-0000";
var pattern = /\d{3}-\d{2}-\d{4}/;
if (pattern.test(text)) {
    console.log("The pattern was matched.");
}
