// 当前正在执行的脚本的文件名的绝对路径
console.log(__filename)
// 当前执行脚本所在的目录
console.log(__dirname)

function printHello() {
    console.log("Hello, World!");
}

// 两秒后执行以上函数
setTimeout(printHello, 2000);

console.log(process)
console.log(global)