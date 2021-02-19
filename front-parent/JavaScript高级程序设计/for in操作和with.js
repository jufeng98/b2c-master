/**
 * Created by yudong on 2017/6/15 0015.

 * for-in 语句是一种精准的迭代语句，可以用来枚举对象的属性。如果表示要迭代的对象的变量值为 null 或 undefined， for-in 语句会抛
 * 出错误。ECMAScript 5 更正了这一行为；对这种情况不再抛出错误，而只是不执行循环体。为了保证最大限度的兼容性，建议在使用 for-in
 * 循环之前，先检测确认该对象的值不是 null 或 undefined。
 *
 * 单独使用和在 for-in 循环中使用。在单独使用时， in 操作符会在通过对象能够访问给定属性时返回 true，无论该属性存在于实例中还是
 * 原型中。在使用 for-in 循环时，返回的是所有能够通过对象访问的、可枚举的（ enumerated）属性，其中既包括存在于实例中的属性，也
 * 包括存在于原型中的属性。
 *
 * 调用"name" in person1 始终都返回 true，无论该属性存在于实例中还是存在于原型中。同时使用 hasOwnProperty() 方法和 in 操作符，
 * 就可以确定该属性到底是存在于对象中，还是存在于原型中

 要取得对象上所有可枚举的实例属性，可以使用 ECMAScript 5 的 Object.keys() 方法。这个方法接收一个对象作为参数，返回一个包含所有
 可枚举属性的字符串数组。

 如果你想要得到所有实例属性，无论它是否可枚举，都可以使用 Object.getOwnPropertyNames()方法。

 with 语句的作用是将代码的作用域设置到一个特定的对象中。严格模式下不允许使用 with 语句，否则将视为语法错误。
 */
var obj = {
    num: 1,
    string: "ss",
    bool: true,
    getNum: function () {
        return this.num;
    }
};
for (var key in obj) {
    console.log('obj key:', key, 'obj value:', obj[key]);
}
var arr = ['John', 'Jack', {'area': 'CN'}, 23]
for (var key in arr) {
    console.log('arr key:', key, 'arr value:', arr[key]);
}
with (obj) {
    console.log('with:', num);
    console.log('with:', string);
    console.log('with:', getNum());
}

function Person() {
}

Person.prototype.name = "Nicholas";
Person.prototype.age = 29;
Person.prototype.job = "Software Engineer";
Person.prototype.sayName = function () {
    console.log(this.name);
};
var person1 = new Person();
var person2 = new Person();
console.log(person1.hasOwnProperty("name")); //false
console.log("name" in person1); //true
person1.name = "Greg";
console.log(person1.name); //"Greg" ——来自实例
console.log(person1.hasOwnProperty("name")); //true
console.log("name" in person1); //true
console.log(person2.name); //"Nicholas" ——来自原型
console.log(person2.hasOwnProperty("name")); //false
console.log("name" in person2); //true
delete person1.name;
console.log(person1.name); //"Nicholas" ——来自原型
console.log(person1.hasOwnProperty("name")); //false
console.log("name" in person1); //true

var keys = Object.keys(Person.prototype);
console.log('keys:', keys); //true
var keys = Object.getOwnPropertyNames(Person.prototype);
console.log('keys:', keys); //true
