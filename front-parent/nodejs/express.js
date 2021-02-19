const express = require('express');
const app = express();

app.get('/', function (req, res) {
    console.log(req.method + " " + req.url + "HTTP/" + req.httpVersion + "\r\n")
    console.log(req.headers)
    console.log("\r\n")
    console.log(req.body)
    res.send('Hello World');
})

const server = app.listen(8088, function () {
    console.log(server.address())
    var host = server.address().address
    var port = server.address().port
    console.log("应用实例，访问地址为 http://%s:%s", host, port)
});