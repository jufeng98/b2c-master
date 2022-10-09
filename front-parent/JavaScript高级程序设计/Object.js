/**
 * Created by yudong on 2017/6/15 0015.

 Object 的每个实例都具有下列属性和方法。
  constructor：保存着用于创建当前对象的函数。对于前面的例子而言，构造函数（ constructor）就是 Object() 。
  hasOwnProperty( propertyName) ：用于检查给定的属性在当前对象实例中（而不是在实例的原型中）是否存在。其中，作为参数的属性名
 （ propertyName）必须以字符串形式指定（例如： o.hasOwnProperty("name") ）。
  isPrototypeOf(object) ：用于检查传入的对象是否是传入对象的原型（第 5 章将讨论原型）。
  propertyIsEnumerable( propertyName) ：用于检查给定的属性是否能够使用 for-in 语句（本章后面将会讨论）来枚举。与 hasOwnProperty()
 方法一样，作为参数的属性名必须以字符串形式指定。
  toLocaleString() ：返回对象的字符串表示，该字符串与执行环境的地区对应。
  toString() ：返回对象的字符串表示。
  valueOf() ：返回对象的字符串、数值或布尔值表示。通常与 toString() 方法的返回值相同。
 Object 构造函数也会像工厂方法一样，根据传入值的类型返回相应基本包装类型的实例。把字符串传给 Object 构造函数，就会创建 String
 的实例；而传入数值参数会得到 Number 的实例，传入布尔值参数就会得到 Boolean 的实例。
 */
var obj = new Object();
obj.name = "hello world";
console.log(obj.constructor);
console.log(obj.hasOwnProperty("name"));

for (var propertyName in obj) {
    console.log("key:", propertyName, "value:", obj[propertyName]);
}

var keys = Object.keys(obj);
for (let i = 0; i < keys.length; i++) {
    console.log("key:", keys[i], "value:", obj[keys[i]]);
}

var values = Object.values(obj);
for (let i = 0; i < values.length; i++) {
    console.log("value:", values[i]);
}

var entrys = Object.entries(obj)
for (let i = 0; i < entrys.length; i++) {
    console.log("key:", entrys[i][0], "value:", entrys[i][1]);
}

var jb = {name: "hello world"};
console.log(Object.isPrototypeOf(jb));

var obj = new Object("some text");
console.log(obj instanceof String); //true

var person = { name: "Nicholas" };
// 调用了 Object.preventExtensions()方法后，就不能给 person 对象添加新属性和方法
Object.preventExtensions(person);
person.age = 29;
alert(person.age); //undefined
alert(Object.isExtensible(person));

var person = { name: "Nicholas" };
// 密封对象不可扩展，而
// 且已有成员的[[Configurable]]特性将被设置为 false。这就意味着不能删除属性和方法
Object.seal(person);
alert(Object.isSealed(person));

var person = { name: "Nicholas" };
// 最严格的防篡改级别是冻结对象（ frozen object）。冻结的对象既不可扩展，又是密封的，而且对象
// 数据属性的[[Writable]]特性会被设置为 false。
Object.freeze(person);
alert(Object.isFrozen(person));