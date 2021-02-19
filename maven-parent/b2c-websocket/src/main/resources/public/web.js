/**
 * Created by yu on 2017/11/10.
 */
var i = 0;
var url = "ws://localhost:9600/macro";
var sock = new WebSocket(url);

sock.onopen = function () {
    document.getElementById("content").innerText = "websocket open";
    sayMacro();
};

sock.onclose = function () {
    document.getElementById("content").innerText = "websocket close";
};

sock.onmessage = function (e) {
    document.getElementById("content").innerText = e.data;
    setTimeout(sayMacro, 1000);
};

function sayMacro() {
    sock.send("important js message " + i++);
}