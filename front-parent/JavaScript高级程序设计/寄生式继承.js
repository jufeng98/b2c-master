/**
 * Created by yudong on 2017/6/17 0017.
 *
 * object() 对传入其中的对象执行了一次浅复制。
 */
function object(o) {
    function F() {
    }

    F.prototype = o;
    return new F();
}

var person = {
    name: "Nicholas",
    friends: ["Shelby", "Court", "Van"]
};
var anotherPerson = object(person);
anotherPerson.name = "Greg";
anotherPerson.friends.push("Rob");
var yetAnotherPerson = object(person);
yetAnotherPerson.name = "Linda";
yetAnotherPerson.friends.push("Barbie");
console.log(person.friends); //"Shelby,Court,Van,Rob,Barbie"