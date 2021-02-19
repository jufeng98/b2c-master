/**
 * Created by yudong on 2017/6/16 0016.
 *
 * 工厂模式虽然解决了创建多个相似对象的问题，但却没有解决对象识别的问题（即怎样知道一个对象的类型）。
 */
function createPerson(name, age, job) {
    var person = new Object();
    person.name = name;
    person.age = age;
    person.job = job;
    person.sayName = function () {
        return this.name;
    };
    return person;
}

var person1 = createPerson("yu", 12, "java");
console.log(person1.sayName());
var person2 = createPerson("dong", 19, "java good");
console.log(person2.sayName());
