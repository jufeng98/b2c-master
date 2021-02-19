/**
 * Created by yu on 2018/3/20.
 */
var socket = new WebSocket("ws://localhost:9600/websocket/macro");
socket.onmessage = function (event) {
    var data = event.data;
    console.log(data);
};
socket.onopen = function () {
    console.log("Connection established.");
};
socket.onerror = function () {
    console.log("Connection error.");
};
socket.onclose = function (event) {
    console.log("Was clean? " + event.wasClean + " Code=" + event.code + " Reason="
        + event.reason);
};

setInterval(function () {
    var message = {
        time: new Date(),
        text: "Hello world!",
        clientId: "asdfp8734rew",
        time: Math.ceil(Math.random() * 10)
    };
    socket.send(JSON.stringify(message));
}, 2000);
