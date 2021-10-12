/**
 * Created by yudong on 2017/6/15 0015

 * ECMAScript 中的函数在定义时不必指定是否返回值。return 语句也可以不带有任何返回值。在这种情况下，函数在停止执行后将返回 undefined
 * 值。.
 严格模式对函数有一些限制：
  不能把函数命名为 eval 或 arguments；
  不能把参数命名为 eval 或 arguments；
  不能出现两个命名参数同名的情况。
 ECMAScript 函数不介意传递进来多少个参数，也不在乎传进来参数是什么数据类型。实际上，在函数体内可以通过 arguments 对象来访问这个
 参数数组，从而获取传递给函数的每一个参数。另一个与参数相关的重要方面，就是 arguments 对象可以与命名参数一起使用,关于 arguments
 的行为，还有一点比较有意思。那就是它的值永远与对应命名参数的值保持同步。
 arguments 对象还有一个名叫 callee 的属性，该属性是一个指针，指向拥有这个 arguments 对象的函数。要访问函数的指针而不执行函数的
 话，必须去掉函数名后面的那对圆括号。
 每个函数都包含两个属性： length 和 prototype。length 属性表示函数希望接收的命名参数的个数每个函数都包含两个非继承而来的方法：
 apply() 和 call() 。这两个方法的用途都是在特定的作用域中调用函数，实际上等于设置函数体内 this 对象的值。首先， apply() 方法接
 收两个参数：一个是在其中运行函数的作用域，另一个是参数数组。其中，第二个参数可以是 Array 的实例，也可以是arguments 对象。在使用
 call() 方法的情况下，必须明确地传入每一个参数。
 ECMAScript 5 还定义了一个方法： bind() 。这个方法会创建一个函数的实例，其 this 值会被绑定到传给 bind() 函数的值。
 每个函数继承的 toLocaleString() 和 toString() 方法始终都返回函数的代码。另外一个继承的valueOf() 方法同样也只返回函数代码。
 */
function sum(num1, num2) {
    return num1 + num2;
}

function sayHi(name, message) {
    return;
    alert("Hello " + name + "," + message); //永远不会调用
}

function howManyArgs() {
    return (arguments.length);
}

function doAdd(num1, num2) {
    if (arguments.length == 1) {
        console.log((num1 + 10));
    } else if (arguments.length == 2) {
        console.log((arguments[0] + num2));
    }
}

console.log(howManyArgs("string", 45)); //2
console.log(howManyArgs()); //0
console.log(howManyArgs(12)); //1
sayHi();
console.log(sum(1, 2));

function sum(num1, num2) {
    return num1 + num2;
}

var sum = function (num1, num2) {
    return num1 + num2;
};
var sum = new Function("num1", "num2", "return num1 + num2"); // 不推荐
function callSum1(num1, num2) {
    return sum.apply(this, arguments); // 传入 arguments 对象
}

function callSum2(num1, num2) {
    return sum.apply(this, [num1, num2]); // 传入数组
}

function callSum3(num1, num2) {
    return sum.call(this, num1, num2);
}

console.log(callSum1(2, 3));
console.log(callSum2(2, 3));
console.log(callSum3(2, 3));
var arr = [{age: 15, name: "liang"}, {age: 12, name: "yu"}, {age: 18, name: "dong"}];

function createCompareFunction(name) {
    return function (obj1, obj2) {
        return obj1.age - obj2.age;
    };
}

console.log(arr.sort(createCompareFunction("age")));

var factorial = function (n) {
    if (n === 1) {
        return 1;
    }
    return n * arguments.callee(n - 1);
};
console.log(factorial(5));

let window = {};
window.color = "red";
var o = {color: "blue"};

function sayColor() {
    console.log(this.color);
}

sayColor(); //red
sayColor.call(this); //red
sayColor.call(window); //red
sayColor.call(o); //blue

window.color = "red";
var o = {color: "blue"};

function sayColor() {
    console.log(this.color);
}

var objectSayColor = sayColor.bind(o);
objectSayColor(); //blue

var immediate = function (num) {
    return num * num;
}(5);
console.log(immediate);

function callMethod(obj, method) {
    let args = [].slice.call(arguments, 2);
    return obj[method].apply(obj, args);
}

let obj = {
    add: function (a, b) {
        return a + b;
    }
}

let result = callMethod(obj, "add", 17, 25);
console.log(result);

let buffer = {
    entries: [],
    add(a) {
        this.entries.push(a);
    },
    concat() {
        return this.entries.join("");
    }
}
let source = ["125", "-", "769"];
source.forEach(buffer.add, buffer);
console.log(buffer.concat())
source.forEach(buffer.add.bind(buffer));
console.log(buffer.concat())

function combineUrl(protocol, domain, path) {
    return `${protocol}://${domain}/${path}`;
}

let paths = ["/search", "/index"]
// 函数柯里化
paths = paths.map(combineUrl.bind(null, "http", "www.baidu.com"))
console.log(paths)