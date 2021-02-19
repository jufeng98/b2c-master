/**
 * Created by yu on 2018/3/20.
 *
 * JSONP的使用
 */
let script = document.createElement("script");
// 此链接服务器返回的内容是类似这样的: createSid({"name":"liang yudong","age":23,"company":"bluemoon"})
script.src = "http://localhost:8520/websocket/createSid?callback=createSid";
document.body.appendChild(script, document.body.firstChild);
// 使用jQuery
$.ajax({
    url: "http://localhost:8520/websocket/createSid",
    dataType: "jsonp",
    jsonp: "callback", // 默认为callback
    jsonpCallback: "createSid"
}).done(function (res) {
    console.log("Ajax Done:", res);
}).fail(function (res, errMsg, errInfo) {
    console.log("Ajax fail Done:", res);
    console.log("Ajax fail Done:", errMsg);
    console.log("Ajax fail Done:", errInfo);
});

function createSid(response) {
    console.log("company:", response.company, ", name:", response.name, ", age:", response.age);
}
