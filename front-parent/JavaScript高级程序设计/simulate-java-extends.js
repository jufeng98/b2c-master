/**
 * Created by yudong on 2017/6/14 0014.
 */
function Person(name) {
    this.name = name;
}

Person.prototype.getName = function () {
    return this.name;
}

function User(name, age) {
    Person.call(this, name);
    this.age = age;
}

inheritPrototype(User, Person);

User.prototype.getAge = function () {
    return this.age;
}

var user = new User("liangyudong", 18);
console.log(user.getName());
console.log(user.getAge());

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