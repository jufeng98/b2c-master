extractVariable();

function extractVariable() {
    // 变量结构赋值,只要等号两边的模式相同，左边的变量就会被赋予对应的值
    let [name, age] = ['liang yudong', 28]
    console.log(name, age)

    let [book, [count]] = ['to success', [23]]
    console.log(book, count)

    // 指定默认值,ES6内部使用严格相等运算符（===），判断一个位置是否有值。所以，如果一个数组成员不严格等于undefined，默认值是
    // 不会生效的。
    let [company = 'bluemoon', size] = [, 'big']
    console.log(company, size)

    let [a, b, c, d, e] = 'hello'
    console.log(a, b, c, d, e)

    // 对象的解构与数组有一个重要的不同。数组的元素是按次序排列的，变量的取值由它的位置决定；而对象的属性没有次序，变量必须与属
    // 性同名，才能取到正确的值。
    let {foo, bar} = {foo: "aaa", bar: "bbb"};
    console.log(foo, bar)

    let {mobile = 'iphone', color} = {color: 'gold'}
    console.log(mobile, color)

    let {sin, cos, log} = Math;
    console.log(sin(60), cos(60), log(10))

    let jsonData = {
        id: 42,
        status: "OK",
        data: [867, 5309]
    };
    let {id, status, data: number} = jsonData;
    console.log(id, status, number);

    let map = new Map()
    map.set('hang', 12)
    map.set('fold', 23)
    for (let [packageType, count] of map) {
        console.log(packageType, count)
    }
}

extractVariableWithDefault([2, , 23]);

function extractVariableWithDefault([x, y = 8, z]) {
    console.log(x, y, z)
}

extractVariableWithDefaultObj({color: 'black'})

/**
 * 双重默认值
 * @param mobile
 * @param color
 */
function extractVariableWithDefaultObj({mobile = 'iphone8s', color} = {}) {
    console.log(mobile, color)
}

let [x, y, z] = extractVariableWithReturn()
console.log(x, y, z)

function extractVariableWithReturn() {
    return [12, 22, 32]
}

let {foo, bar} = extractVariableWithReturnObj();
console.log(foo, bar)

function extractVariableWithReturnObj() {
    return {foo: 'hello', bar: 'world'}
}
