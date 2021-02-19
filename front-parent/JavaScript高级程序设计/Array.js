/**
 * Created by yudong on 2017/6/15 0015.

 ECMAScript 数组的每一项可以保存任何类型的数据。也就是说，可以用数组的第一个位置来保存字符串，用第二位置来保存数值，用第三个位置
 来保存对象，以此类推。而且， ECMAScript 数组的大小是可以动态调整的，即可以随着数据的添加自动增长以容纳新增数据。

 栈方法
 push() 和 pop()

 队列方法
 shift() 和 unshift()，同时使用 unshift() 和 pop() 方法，可以从相反的方向来模拟队列，即在数组的前端添加项，从数组末端移除项

 重排序方法
 reverse() 和 sort()，sort() 方法可以接收一个比较函数作为参数

 操作方法
 concat() 方法可以基于当前数组中的所有项创建一个新数组
 slice()
 方法可以接受一或两个参数，即要返回项的起始和结束位置。在只有一个参数的情况下， slice() 方法返回从该参数指定位置开始到当前数组末
 尾的所有项。如果有两个参数，该方法返回起始和结束位置之间的项——但不包括结束位置的项。注意， slice() 方法不会影响原始数组，类似于
 字符串的substring()方法
 splice()
  删除：可以删除任意数量的项，只需指定 2 个参数：要删除的第一项的位置和要删除的项数。例如， splice(0,2) 会删除数组中的前两项。
  插入：可以向指定位置插入任意数量的项，只需提供 3 个参数：起始位置、 0（要删除的项数）和要插入的项。如果要插入多个项，可以再传
 入第四、第五，以至任意多个项。例如，splice(2,0,"red","green") 会从当前数组的位置 2 开始插入字符串"red" 和"green" 。
  替换：可以向指定位置插入任意数量的项，且同时删除任意数量的项，只需指定 3 个参数：起始位置、要删除的项数和要插入的任意数量的项。
 插入的项数不必与删除的项数相等。例如，splice (2,1,"red","green") 会删除当前数组位置 2 的项，然后再从位置 2 开始插入字符串"red"
 和"green"。splice() 方法始终都会返回一个数组，该数组中包含从原始数组中删除的项（如果没有删除任何项，则返回一个空数组）。

 位置方法
 indexOf() 和 lastIndexOf()

 迭代方法
 每个方法都接收两个参数：要在每一项上运行的函数和（可选的）运行该函数的作用域对象——影响 this 的值。传入这些方法中的函数会接收三个
 参数：数组项的值、该项在数组中的位置和数组对象本身。根据使用的方法不同，这个函数执行后的返回值可能会也可能不会影响方法的返回值。
 以下是这 5 个迭代方法的作用：
  every() ：对数组中的每一项运行给定函数，如果该函数对每一项都返回 true，则返回 true。
  filter() ：对数组中的每一项运行给定函数，返回该函数会返回 true 的项组成的数组。
  forEach() ：对数组中的每一项运行给定函数。这个方法没有返回值。
  map() ：对数组中的每一项运行给定函数，返回每次函数调用的结果组成的数组。
  some() ：对数组中的每一项运行给定函数，如果该函数对任一项返回 true，则返回 true。
 以上方法都不会修改数组中的包含的值。

 归并方法
 reduce() 和 reduceRight()
 这两个方法都会迭代数组的所有项，然后构建一个最终返回的值。其中， reduce() 方法从数组的第一项开始，逐个遍历到最后。而 reduceRight()
 则从数组的最后一项开始，向前遍历到第一项。这两个方法都接收两个参数：一个在每一项上调用的函数和（可选的）作为归并基础的初始值。传
 给 reduce() 和 reduceRight() 的函数接收 4 个参数：前一个值、当前值、项的索引和数组对象。这个函数返回的任何值都会作为第一个参数
 自动传给下一项。第一次迭代发生在数组的第二项上，因此第一个参数是数组的第一项，第二个参数就是数组的第二项。
 */
var arr = new Array(1, "23");
arr["string"] = "wazoo,good job";
console.log(arr.length);
console.log(Array.isArray(arr));
console.log(Array.isArray("arr"));
console.log(arr[1]);
console.log(arr["string"]);
console.log('------');

var arr2 = [1, "23", {name: "world"}];
console.log(arr2[0]);
console.log('------');

var arr3 = [12, "qq", true];
console.log(arr3);
console.log(arr3.pop());
console.log('join:', arr3.join("|||"));
console.log('------');

var colors = ["red", "green", "blue", 12, 23];
var colors2 = colors.concat("yellow", ["black", "brown"]);
console.log('concat:', colors2);
console.log('------');

var colors = ["red", "green", "blue"];
var removed = colors.splice(0,1); // 删除第一项
console.log(colors); // green,blue 
console.log(removed); // red，返回的数组中只包含一项
removed = colors.splice(1, 0, "yellow", "orange"); // 从位置 1 开始插入两项
console.log(colors); // green,yellow,orange,blue 
console.log(removed); // 返回的是一个空数组
removed = colors.splice(1, 1, "red", "purple"); // 插入两项，删除一项
console.log(colors); // green,red,purple,orange,blue 
console.log(removed); // yellow，返回的数组中只包含一项
console.log('------');

var bool = colors2.every(function (item, index, arr) {
    console.log(item + " " + index + " " + arr);
    return true;
});
console.log('every:', bool);
console.log('------');

var arrnum = colors2.filter(function (item, index, arr) {
    if (typeof item == "number") {
        return true;
    }
});
console.log('filter:', arrnum);
console.log('------');

let personList = [
    {"name": "Jack", age: 20},
    {"name": "Rose", age: 18},
    {"name": "John", age: 22}
];
let tmpPersonList = personList.filter((item) => {
    if (item.age === 20) return true;
    return false;
});

console.log('filter:', tmpPersonList);
console.log('------');

tmpPersonList = personList.map((item) => {
    return item.name;
})
console.log('map:', tmpPersonList);
console.log('------');

tmpPersonList = personList.sort((item1, item2) => {
    return item1.age - item2.age;
})
console.log('sort:', tmpPersonList);
console.log('------');

var numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9];
var sum = numbers.reduce(function (previousItem, currentItem, index, arr) {
    return previousItem + currentItem;
}, 0);
console.log('reduce:', sum);
console.log('------');