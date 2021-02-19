// Set内部判断两个值是否不同，使用的算法叫做“Same-value equality”，它类似于精确相等运算符（===），主要的区别是NaN等于自身，而
// 精确相等运算符认为NaN不等于自身。所以,两个对象总是不相等的
let numSet = new Set()
numSet.add(1).add(7)
numSet.add(1)
numSet.add(3)
numSet.add(5)
console.log(numSet)
console.log(numSet.keys(), numSet.values(), numSet.entries())
numSet.forEach(((value, key) => {
    console.log(key, value)
}))
for (let num of numSet.values()) {
    console.log(num)
}

let items = new Set([1, 2, 3, 4, 5, 5, 5, 5]);
console.log(items)

let array = Array.from(items);
console.log(array)

console.log(filterRepeatEle([1, 5, 8, 5, 8]))

function filterRepeatEle(array) {
    return Array.from(new Set(array))
}

let map = new Map();
map.set(1, 'hang')
map.set(2, 'fold')
console.log(map)
map = new Map([
    ['name', '张三'],
    ['title', 'Author']
]);
console.log(map, map.size)
console.log(map.get(2))
// Map的键是对象时,实际上是跟内存地址绑定的，只要内存地址不一样，就视为两个键。
map.set(['a'], 555);
console.log(map.get(['a']))
console.log(map, map.has('name'))
console.log(map, map.delete('title'))
for (let entry of map.entries()) {
    console.log(entry)
}
map.forEach(function (value, key, map) {
    console.log("Key: %s, Value: %s", key, value);
});