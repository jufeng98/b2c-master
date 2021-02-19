/**
 * Created by yudong on 2017/6/19 0019.
 */
function SuperType(name) {
    this.name = name;
    this.colors = ["red", "blue", "green"];
    if (typeof SuperType.sayName !== "function") {
        SuperType.prototype.sayName = function () {
            console.log(this.name);
        };
    }
}

function SubType(name, age) {
    SuperType.call(this, name);
    this.age = age;
    if (typeof SubType.sayAge !== "function") {
        SubType.prototype.sayAge = function () {
            console.log(this.age);
        };
    }
}

inheritPrototype(SubType, SuperType);

var sub = new SubType("yu", 23);
sub.sayAge();
sub.sayName();
sub.colors.push("pink");
var sub1 = new SubType("liang", 21);
sub1.sayAge();
sub1.sayName();
console.log(sub1.colors.join(" "));

function inheritPrototype(subType, superType) {
    var tempProto = object(superType.prototype); //创建对象
    tempProto.constructor = subType; //增强对象
    subType.prototype = tempProto; //指定对象
}

function object(o) {
    function F() {
    }

    F.prototype = o;
    return new F();
}