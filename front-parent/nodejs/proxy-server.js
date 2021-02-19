let http = require('https')
let url = require('url')
let server = http.createServer((sreq, sres) => {
    let urlParts = url.parse(sreq.url)
    let options = {hostname: "www.bejson.com", port: 80, path: urlParts.pathname, headers: sreq.headers}
    let creq = http.get(options, cres => {
        sres.writeHead(cres.statusCode, cres.headers)
        cres.pipe(sres)
    })
    sreq.pipe(creq)
})
server.listen(9632,'127.0.0.1')
console.log('proxy server running on http://127.0.0.1:9632')
