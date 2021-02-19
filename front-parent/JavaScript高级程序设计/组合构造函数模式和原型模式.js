/**
 * Created by yudong on 2017/6/16 0016.
 */
function Person(name, age, job) {
    this.name = name;
    this.age = age;
    this.job = job;
}

Person.prototype.sayName = function () {
    return this.name;
};
var person1 = new Person("yu", 12, "java");
console.log(person1.sayName());
var person2 = new Person("dong", 19, "java good");
console.log(person2.sayName());
console.log(person1.sayName === person2.sayName);