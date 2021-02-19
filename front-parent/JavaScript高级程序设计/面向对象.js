/**
 * Created by yudong on 2017/6/16 0016.
 *
 * ECMA-262 把对象定义为：“无序属性的集合，其属性可以包含基本值、对象或者函数。”我们可以把 ECMAScript 的对象想象成散列表：无非
 * 就是一组名值对，其中值可以是数据或函数。
 */
var person = new Object();
person.name = "liang yudong";
person.age = 19;
person.job = "java engineer";
person.show = function () {
    return this.job;
}
console.log(person.show());
var person = {
    name: "liang yudong",
    age: 18,
    job: "java engineer",
    show: function () {
        return this.job;
    }
};
console.log(person.show());
for (var prop in person) {
    console.log(prop);
}