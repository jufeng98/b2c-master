function getDataFromServer() {
    return new Promise(function (resolve, reject) {
        // 模拟请求阻塞时间
        setTimeout(function () {
            let rand = Math.ceil(Math.random() * 1000)
            if (rand % 2 === 0) {
                resolve('success')
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
                resolve('success ' + data)
            } else {
                reject('failed')
            }
        }, 2000)
    });
}

getDataFromServer()
    .then(value => {
        console.log(value)
        return getDataFromServer1(value)
    })
    .then(value => console.log(value))
    .catch(reason => console.log(reason))
    .finally(() => console.log("release resource"))

Promise.all([getDataFromServer(), getDataFromServer1("date")])
    .then((r1, r2) => console.log(r1, r2))
    .catch(reason => console.log(reason))
