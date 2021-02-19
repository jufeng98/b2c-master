/**
 * Created by yudong on 2017/6/16 0016.
 只要创建了一个新函数，就会根据一组特定的规则为该函数创建一个 prototype属性，这个属性指向函数的原型对象。在默认情况下，所有原型
 对象都会自动获得一个 constructor（构造函数）属性，这个属性包含一个指向 prototype 属性所在函数的指针。
 虽然可以通过对象实例访问保存在原型中的值，但却不能通过对象实例重写原型中的值。如果我们在实例中添加了一个属性，而该属性与实例原型
 中的一个属性同名，那我们就在实例中创建该属性，该属性将会屏蔽原型中的那个属性。
 当为对象实例添加一个属性时，这个属性就会屏蔽原型对象中保存的同名属性；换句话说，添加这个属性只会阻止我们访问原型中的那个属性，但
 不会修改那个属性。即使将这个属性设置为 null，也只会在实例中设置这个属性，而不会恢复其指向原型的连接。不过，使用 delete 操作符则
 可以完全删除实例属性，从而让我们能够重新访问原型中的属性
 使用 hasOwnProperty() 方法可以检测一个属性是存在于实例中，还是存在于原型中。这个方法（不要忘了它是从 Object 继承来的）只在给定
 属性存在于对象实例中时，才会返回 true。

 为减少不必要的输入，也为了从视觉上更好地封装原型的功能，更常见的做法是用一个包含所有属性和方法的对象字面量来重写整个原型对象.我
 们将 Person.prototype 设置为等于一个以对象字面量形式创建的新对象。最终结果相同，但有一个例外： constructor 属性不再指向 Person
 了。前面曾经介绍过，每创建一个函数，就会同时创建它的 prototype 对象，这个对象也会自动获得 constructor 属性。而我们在这里使用的
 语法，本质上完全重写了默认的 prototype 对象，因此 constructor 属性也就变成了新对象的 constructor 属性（指向 Object 构造函数），
 不再指向 Person 函数。此时，尽管 instanceof操作符还能返回正确的结果，但通过 constructor 已经无法确定对象的类型了

 原型模式的重要性不仅体现在创建自定义类型方面，就连所有原生的引用类型，都是采用这种模式创建的。所有原生引用类型（ Object、 Array、
 String，等等）都在其构造函数的原型上定义了方法。
 例如，在 Array.prototype 中可以找到 sort() 方法，而在 String.prototype 中可以找到substring() 方法
 通过原生对象的原型，不仅可以取得所有默认方法的引用，而且也可以定义新方法。可以像修改自定义对象的原型一样修改原生对象的原型，因此
 可以随时添加方法。
 */
function Person() {
}

Person.prototype.name = "Nicholas";
Person.prototype.age = 29;
Person.prototype.job = "Software Engineer";
Person.prototype.sayName = function () {
    return this.name;
};
var person1 = new Person();
console.log(person1.sayName());
var person2 = new Person();
console.log(person2.sayName());
console.log(person1.sayName === person2.sayName);

console.log(Person.prototype.isPrototypeOf(person1)); //true
console.log(Person.prototype.isPrototypeOf(person2)); //true
console.log(Object.getPrototypeOf(person1) == Person.prototype); //true
console.log(Object.getPrototypeOf(person1).name); //"Nicholas"

person1.name = "Greg";
console.log(person1.name); //"Greg"——来自实例
console.log(person1.hasOwnProperty("name"));
console.log(person2.name); //"Nicholas"——来自原型
delete person1.name;
console.log(person1.name);
console.log(person1.hasOwnProperty("name"));

function Person() {
}

Person.prototype = {
    name: "Nicholas",
    age: 29,
    job: "Software Engineer",
    sayName: function () {
        alert(this.name);
    }
};