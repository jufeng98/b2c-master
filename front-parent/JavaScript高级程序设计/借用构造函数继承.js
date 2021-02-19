/**
 * Created by yudong on 2017/6/17 0017.
 * 借用构造函数，那么也将无法避免构造函数模式存在的问题——方法都在构造函数中定
 义，因此函数复用就无从谈起了。而且，在超类型的原型中定义的方法，对子类型而言也是不可见的，结
 果所有类型都只能使用构造函数模式。考虑到这些问题，借用构造函数的技术也是很少单独使用的。
 */
function SuperType() {
    this.colors = ["red", "blue", "green"];
}

function SubType() {
    //继承了 SuperType
    SuperType.call(this);
}

var instance1 = new SubType();
instance1.colors.push("black");
console.log(instance1.colors); //"red,blue,green,black"
var instance2 = new SubType();
console.log(instance2.colors); //"red,blue,green"