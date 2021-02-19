stringExtend()

function stringExtend() {

    let name = '囕囖圙圚'
    for (let c of name) {
        console.log(c)
    }

    let company = '蓝月亮中国有限公司'
    console.log(company.includes('中国'))
    console.log(company.startsWith('蓝'))
    console.log(company.endsWith('公司'))

    console.log('hello world'.repeat(3))

    let welcome = `hello ${name} ,your company is ${company},
    welcome to the big family,you random code is ${Math.ceil(Math.random() * 100000)}`
    console.log(welcome)
}
