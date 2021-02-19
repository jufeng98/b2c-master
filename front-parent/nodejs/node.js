let http = require('http')
let person = require('./person')
let suites = require('./suites')
let server = http.createServer((req, res) => {
    console.log("请求:")
    let rawReqContent = ''
    // 请求首行
    rawReqContent += req.method + ' ' + req.url + ' HTTP/' + req.httpVersion + '\r\n'
    // 请求头
    for (let arr of Object.entries(req.headers)) {
        rawReqContent += arr[0] + ':' + arr[1] + '\r\n'
    }
    console.log(rawReqContent)
    // 请求体
    req.addListener('data', function (chunk) {
        console.log(decodeURIComponent(chunk))
    })
    if (req.url.indexOf('/getPersonInfo') !== -1) {
        res.writeHead(200, {
            'content-type': 'application/json',
            'Set-Cookie': 'JSESSIONID=75FF4AC74213A1C725DEAD507112BE96; Path=/; HttpOnly'
        })
        res.write(JSON.stringify(person))
        res.end()
    } else if (req.url.indexOf('/greeting') !== -1) {
        res.writeHead(200, {
            'content-type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': '*',
            'Access-Control-Expose-Headers': 'Content-Length, X-JSON'
        })
        res.write(JSON.stringify({'id': 234334543432, 'content': 'important message from server'}))
        res.end()
    } else if (req.url.indexOf('/personHtml') !== -1) {
        res.writeHead(200, {
            'content-type': 'text/html',
            'Access-Control-Allow-Origin': '*'
        })
        res.write(`
           <label>姓名:</label>liang yudong</br>
           <label>性别:</label>man</br>
           <label>公司:</label>bluemoon</br>
        `)
        res.end()
    } else if (req.url.indexOf('callback') !== -1) {
        // jsonp
        res.writeHead(200, {
            'content-type': 'application/javascript',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': '*',
            'Access-Control-Allow-Methods': '*',
            'Set-Cookie': 'JSESSIONID=75FF4AC74213A1C725DEAD507112BE96; Path=/; HttpOnly'
        })
        let value = getParameter(req.url, 'callback')
        res.write(value + '(' + JSON.stringify(person) + ')')
        res.end()
    } else if (req.url.indexOf('/getSuites') !== -1) {
        res.writeHead(200, {
            'content-type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': '*',
        })
        res.write(JSON.stringify(suites))
        res.end()
    } else {
        res.writeHead(200, {'content-type': 'text/html'})
        res.write('welcome to the nodejs server!')
        res.end()
    }
})
server.listen(8520, '127.0.0.1')
console.log('server running at http://127.0.0.1:8520')

function getParameter(url, name) {
    const queryString = url.substring(url.indexOf('?') + 1);
    const pairs = queryString.split("&");
    for (let i = 0; i < pairs.length; i++) {
        const pair = pairs[i].split('=');
        if (pair[0] == name) {
            return pair[1];
        }
    }
}

//server.once('request', (req, res) => console.log(`初次接收客户端请求的时间:${new Date()}`))

// server.addListener('request', (req, res) => {
//     console.log('请求完成')
//     res.end()
// })