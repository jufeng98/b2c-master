var axios = require('axios');
jsdom = require('jsdom');
var {JSDOM} = jsdom;
document = new JSDOM("", {
    url: "http://localhost:8520",
    //referrer: "",
    contentType: "text/html",
    includeNodeLocations: true,
    storageQuota: 10000000
});
var $ = require('jquery')(document.window)
axios.get("http://localhost:8520/greeting", {params: {name: '梁煜东'}})
    .then(function (response) {
        console.log('data:', response.data);
    })
    .catch(function (error) {
        console.log('error', error);
    });

axios.post("http://localhost:8520/greeting", {params: {name: '梁煜东'}})
    .then(function (response) {
        console.log('data:', response.data);
    })
    .catch(function (error) {
        console.log('error', error);
    });

function getUserAccount() {
    return axios.get('http://localhost:8520/greeting');
}

function getUserPermissions() {
    return axios.get('http://localhost:8520/getPersonInfo');
}

// 并发执行请求
axios.all([getUserAccount(), getUserPermissions()])
    .then(axios.spread(function (res1, res2) {
        // 两个请求现在都执行完成
        console.log('multiply request:', res1.data, res2.data);
    }));

axios({
    url: '/greeting',
    method: 'post',
    baseURL: 'http://localhost:8520',
    data: {
        firstName: 'Fred',
        lastName: 'Flintstone'
    }
}).then(function (response) {
    console.log('data:', response.data);
});
// 全局的 axios 默认值
axios.defaults.headers.common['Authorization'] = 'ETGDLGJFNXVNLXJDFSLJFDIEF';
axios({
    url: '/greeting',
    method: 'post',
    baseURL: 'http://localhost:8520',
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    data: {
        firstName: '梁',
        lastName: '煜东'
    },
    transformRequest: [function (data) {
        return $.param(data);
    }],
}).then(function (response) {
    console.log('data:', response.data);
});
