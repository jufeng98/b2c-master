new Ajax.Request(
    "http://127.0.0.1:8520/greeting",
    {method: 'post', parameters: "ID=222&name=chen", onComplete: printResponse}
);

function printResponse(klass) {
    console.log('klass:', klass)
}

var btn = document.getElementById("showValue");
btn.onclick = function () {
    console.log($F('username'))
};