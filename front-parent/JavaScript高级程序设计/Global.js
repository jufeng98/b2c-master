/**
 * Created by yudong on 2017/6/16 0016.

 Global 对象的所有属性
 属 性         说 明                            属 性                 说 明
 undefined     特殊值undefined                  Date                 构造函数Date
 NaN           特殊值NaN                        RegExp               构造函数RegExp
 Infinity      特殊值Infinity                   Error                构造函数Error
 Object        构造函数Object                   EvalError            构造函数EvalError
 Array         构造函数Array                    RangeError           构造函数RangeError
 Function      构造函数Function                 ReferenceError       构造函数ReferenceError
 Boolean       构造函数Boolean                  SyntaxError          构造函数SyntaxError
 String        构造函数String                   TypeError            构造函数TypeError
 Number        构造函数Number                   URIError             构造函数URIError

 ECMAScript 虽然没有指出如何直接访问 Global 对象，但 Web 浏览器都是将这个全局对象作为window 对象的一部分加以实现的。因此，在全
 局作用域中声明的所有变量和函数，就都成为了 window对象的属性。
 */
var color = "red";

function sayColor() {
    console.log(window.color);
}

window.sayColor(); //"red"

var global = function () {
    return this;
}();