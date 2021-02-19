/**
 * Created by yu on 2018/3/20.
 */
function logError(level, msg, stack) {
    var img = new Image();
    img.src = "http://127.0.0.1:9600/websocket/JSLogger?level=" + encodeURIComponent(level) + "&msg=" + encodeURIComponent(msg) + "&stack=" + encodeURIComponent(stack);
}

function throwError() {
    try {
        if (1 === 1) {
            throw new Error("test error msg");
        }
    } catch (e) {
        console.log(e.message);
        console.log(e.stack);
        logError("severe", e.message, e.stack);
    }
}

setTimeout(throwError, 3000);