/**
 * Created by yudong on 2017/6/19 0019.
 */
function Person(name) {
    this.getName = function () {
        return name;
    };
    this.setName = function (value) {
        name = value;
    };
}

var person = new Person("Nicholas");
console.log(person.getName()); //"Nicholas"
person.setName("Greg");
console.log(person.getName()); //"Greg"


(function () {
    var name = "";
    Person = function (value) {//前面没使用var声明
        name = value;
    };
    Person.prototype.getName = function () {
        return name;
    };
    Person.prototype.setName = function (value) {
        name = value;
    };
})();
var person1 = new Person("Nicholas");
console.log(person1.getName()); //"Nicholas"
person1.setName("Greg");
console.log(person1.getName()); //"Greg"
var person2 = new Person("Michael");
console.log(person1.getName()); //"Michael"
console.log(person2.getName());//"Michael"
