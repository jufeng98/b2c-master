function getDataFromServer() {
    return new Promise(function (resolve, reject) {
        // 模拟请求阻塞时间
        setTimeout(function () {
            let rand = Math.ceil(Math.random() * 1000)
            if (rand % 2 === 0) {
                resolve('{}')
            } else {
                reject('failed')
            }
        }, 2000)
    });
}

function getDataFromServer1(data) {
    return new Promise(function (resolve, reject) {
        // 模拟请求阻塞时间
        setTimeout(function () {
            let rand = Math.ceil(Math.random() * 1000)
            if (rand % 2 === 0) {
                resolve('{} ' + data)
            } else {
                reject('failed')
            }
        }, 2000)
    });
}
async function getData(){
    let data = await getDataFromServer()
    return await getDataFromServer1(data);
}

getData()
    .then(r => console.log(r))
    .catch(e => console.log(e));