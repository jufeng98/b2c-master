/**
 * Created by yudong on 2017/6/16 0016.

 eval()方法就像是一个完整的 ECMAScript 解析器，它只接受一个参数，即要执行的 ECMAScript（或 JavaScript）字符串。当解析器发现代
 码中调用 eval() 方法时，它会将传入的参数当作实际的 ECMAScript 语句来解析，然后把执行结果插入到原位置。
 */
var msg = "hello world";
eval("console.log(msg)"); //"hello world"

eval("function sayHi() { console.log('hi'); }");
sayHi();

eval("var msg = 'hello world'; ");
console.log(msg); //"hello world"

eval('show({"name":"liang yudong","age":23,"company":"master"})')

function show(response) {
    console.log("company:", response.company, ", name:", response.name, ", age:", response.age);
}