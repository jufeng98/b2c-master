/**
 * Created by yudong on 2017/6/20 0020.

 * BOM 的核心对象是 window，它表示浏览器的一个实例。在浏览器中， window 对象有双重角色，它既是通过 JavaScript 访问浏览器窗口的
 * 一个接口，又是 ECMAScript 规定的 Global 对象。由于 window 对象同时扮演着 ECMAScript 中 Global 对象的角色，因此所有在全局作
 * 用域中声明的变量、函数都会变成 window 对象的属性和方法。
 * 全局变量不能通过 delete 操作符删除，而直接在 window 对象上的定义的属性可以。
 */
var age = 29;

function sayAge() {
    alert(this.age);
}

alert(window.age); //29
sayAge(); //29
window.sayAge(); //29

var age = 29;
window.color = "red";
//在 IE < 9 时抛出错误，在其他所有浏览器中都返回 false
delete window.age;
//在 IE < 9 时抛出错误，在其他所有浏览器中都返回 true
delete window.color; //returns true
alert(window.age); //29
alert(window.color); //undefined
