//缓存原有方法
mini._ajax = mini.ajax;
//添加 payload 转换方法
mini.payload = function (isPayload) {
    if (isPayload) {
        mini.ajax = function (_options) {
            _options.dataType = "json";
            _options.contentType = "application/json; charset=utf-8";
            _options.data = JSON.stringify(_options.data);
            return mini._ajax(_options)
        }
    } else {
        mini.ajax = mini._ajax;
    }
}
/* 设置ajax头 */
$.ajaxSetup({
    contentType: "application/x-www-form-urlencoded;charset=utf-8"
});
(function () {
    nui.context = location.protocol + "//" + location.host;
})();

var data = {};
nui.DataTree.prototype.dataField = 'data';//兼容改造
//全局兼容改造datagrid的load方法
mini.payload(true);

