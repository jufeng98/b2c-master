function get(id) {
    nuiObj = nui.get(id)
    console.log(nuiObj)
    showResult(nuiObj.value)
}

function getByUID(uid) {
    nuiObj = nui.getbyName(uid)
    console.log(nuiObj)
    showResult(nuiObj.value)
}

function getbyName(name) {
    nuiObj = nui.getbyName(name)
    console.log(nuiObj)
    showResult(nuiObj.value)
}

function getsbyName(name) {
    nuiObj = nui.getsbyName(name)
    console.log(nuiObj)
    showResult(nuiObj[0].value)
}

function formatCurrency(id) {
    nuiObj = nui.get(id)
    showResult(nui.formatCurrency(nuiObj.value))
}

function encode() {
    var param = {
        opTime: 1565597245331,
        opName: 'liang yudong'
    }
    showResult(nui.encode(param))
}

function decode() {
    var param = '{"opTime": "2019-08-12 12:20:20","opName": "liang yudong"}'
    showResult(nui.decode(param).opTime + nui.decode(param).opName)
}

function parseDate() {
    showResult(nui.parseDate("2019-08-12 12:20:20"))
}

function formatDate() {
    showResult(nui.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"))
}

function showResult(resStr) {
    var div = $("#result");
    div.text(div.text() + resStr);
}