var jsdom;
if (typeof document === "undefined") {
    // jsdom = require("C:\\Users\\yu\\node_global\\node_modules\\jsdom");
    jsdom = require('jsdom');
    var {JSDOM} = jsdom;
    document = new JSDOM("", {
        url: "http://localhost:8520",
        //referrer: "",
        contentType: "text/html",
        includeNodeLocations: true,
        storageQuota: 10000000
    });
    $ = require('jquery')(document.window);
} else {
    jsdom = document;
}
$.get("http://localhost:8520/greeting", {name: "梁煜东", age: 22}, function (data, textStatus, jqXHR) {
    console.log(jqXHR.getAllResponseHeaders());
    console.log(data, textStatus, jqXHR.responseText);
});