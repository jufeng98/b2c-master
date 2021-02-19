let hwg = helloWorldGenerator();
console.log(hwg.next())
console.log(hwg.next())
console.log(hwg.next())
console.log(hwg.next())

let hwg1 = helloWorldGenerator();
console.log(hwg1.next())
console.log(hwg1.next(22))
console.log(hwg1.next(32))
console.log(hwg1.next())

function* helloWorldGenerator() {
    let a = yield 'hello';
    console.log(a)
    let b = yield 'world';
    console.log(b)
    return 'ending';
}

