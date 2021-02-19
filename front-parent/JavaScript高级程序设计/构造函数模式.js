/**
 * Created by yudong on 2017/6/16 0016.
 *
 * 按照惯例，构造函数始终都应该以一个大写字母开头，而非构造函数则应该以一个小写字母开头。创建自定义的构造函数意味着将来可以将它
 * 的实例标识为一种特定的类型；而这正是构造函数模式胜过工厂模式的地方。在这个例子中， person1 和 person2 之所以同时是 Object
 * 的实例，是因为所有对象均继承自 Object
 * 构造函数与其他函数的唯一区别，就在于调用它们的方式不同。不过，构造函数毕竟也是函数，不存在定义构造函数的特殊语法。任何函数，
 * 只要通过 new 操作符来调用，那它就可以作为构造函数；而任何函数，如果不通过 new 操作符来调用，那它跟普通函数也不会有什么两样。
 * 此时函数内this指代window对象构造函数模式虽然好用，但也并非没有缺点。使用构造函数的主要问题，就是每个方法都要在每个实例上重新
 * 创建一遍。创建两个完成同样任务的 Function 实例的确没有必要；况且有 this 对象在，根本不用在执行代码前就把函数绑定到特定对象上面。
 */
function Person(name, age, job) {
    this.name = name;
    this.age = age;
    this.job = job;
    this.sayName = function () {
        return this.name;
    };
}

var person1 = new Person("yu", 12, "java");
console.log(person1.sayName());
var person2 = new Person("dong", 19, "java good");
console.log(person2.sayName());
console.log(person1.sayName === person2.sayName);
console.log(person1.constructor == Person); //true
console.log(person2.constructor == Person); //true
console.log(person1 instanceof Object); //true
console.log(person1 instanceof Person); //true
console.log(person2 instanceof Object); //true
console.log(person2 instanceof Person); //true

// 当作构造函数使用
var person = new Person("Nicholas", 29, "Software Engineer");
person.sayName(); //"Nicholas"
// 作为普通函数调用
Person("Greg", 27, "Doctor"); // 添加到 window
window.sayName(); //"Greg"
// 在另一个对象的作用域中调用
var o = new Object();
Person.call(o, "Kristen", 25, "Nurse");
o.sayName(); //"Kristen"