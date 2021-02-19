var axios = require('axios');
jsdom = require('jsdom');
var {JSDOM} = jsdom;
document = new JSDOM("", {
    url: "http://localhost:8520",
    contentType: "text/html",
    includeNodeLocations: true,
    storageQuota: 10000000
});
