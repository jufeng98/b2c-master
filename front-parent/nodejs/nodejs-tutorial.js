let interval = setInterval((name) => {
    console.log(`hello ${name}`)
}, 1000, 'Jack')

setTimeout(() => clearInterval(interval), 3000)

// 对模块的多次require 模块内的代码也只会被执行一次
let tmp = require('./module-test1')
tmp = require('./module-test1')
console.log(tmp)

let {moduleName} = require('./module-test1')
console.log(moduleName)

// 得到模块的完整路径
console.log(require.resolve('./module-test1'))

// 得到已被缓存的所有模块
console.log(require.cache)

// 得到当前模块名,当前目录名
console.log(__filename, __dirname)

console.log(module.id, module.filename, module.loaded, module.parent, module.children)

let buf = Buffer.alloc(32)
buf.writeInt8(48, 0)
buf.writeInt8(49, 1)
console.log(new Number(48).toString(2))
console.log(new Number(49).toString(2))
console.log(buf.readInt32BE(0).toString(2))
console.log(buf.readInt32LE(0).toString(2))

console.log(Buffer.isEncoding('utf-8'))

let fs = require('fs')
let data = fs.readFileSync('./person.json', 'utf-8')
console.log(data)
fs.readFile('./person.json', 'utf-8', (err, data) => {
    console.log(err, data)
})
fs.writeFileSync('./tmp.txt', 'this is first line.\r\nthis is second line.\r\n', (err) => {
    if (!err) {
        console.log('写文件成功')
    }
})

fs = require('fs')
buf = Buffer.alloc(32, 'welcome to nodejs', 'utf-8')
if (fs.existsSync('./temp.txt')) {
    fs.unlinkSync('./temp.txt')
}
fs.open('./temp.txt', 'wx', (err, fd) => {
    fs.write(fd, buf, 0, buf.length, 0, (err, written, buffer) => {
        if (err) {
            console.log('failed to write file')
        } else {
            console.log('write file success')
        }
        fs.close(fd, (a) => {
            if (!a) {
                console.log('close file success');
            }
        })
    })
});
let steam = fs.createReadStream('./tmp.txt')
steam.on('data', (data) => {
    console.log(data)
})

let path = require('path')
console.log(path.basename('./tmp.txt'))
console.log(path.dirname('./tmp.txt'))
console.log(path.resolve('./tmp.txt'))

let http = require('http')
let options = {hostname: "127.0.0.1", port: 8520, path: "/getPersonInfo", method: "POST"}
let req = http.request(options, incomingMessage => {
    incomingMessage.on('data', buffer => {
        console.log('服务器响应:', buffer.toString('utf-8', 0, buffer.length))
    })
})
req.setHeader('token', 'K678HGFU6768968986FFYHGV')
req.write('{"desc":"message from nodejs"}')
req.end()

console.log(process.platform)
console.log(process.execPath)
console.log(process.argv)
console.log(process.config)
