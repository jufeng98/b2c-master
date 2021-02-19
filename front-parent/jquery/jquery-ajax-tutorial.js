// let jsdom = require('C:\\Users\\yu\\node_global\\node_modules\\jsdom');
// (根据环境变量NODE_PATH指定的路径)加载jsdom目录下package.json文件的main属性指定的js文件
let jsdom = require('jsdom');
let {JSDOM} = jsdom;
let htmlStr = `
<html> 
     <body> 
        <div id="n1"> 
          <div id="n2"> 
            <ul id="n3"> 
              <li id="n4">item1</li> 
              <li id="n5">item2</li> 
              <li id="n6">item3</li> 
            </ul> 
          </div> 
          <div id="n7"> 
            <table id="n8"> 
              <tr id="n9"><td>cell1</td></tr> 
              <tr id="n10"><td>cell2</td></tr> 
              <tr id="n11"><td>cell3</td></tr> 
            </table> 
          </div> 
        </div>
		<form id="container">
		  <select name="country">
			<option selected="selected">CHINA</option>
			<option>USA</option>
		  </select>
		  <select name="language" multiple="multiple">
			<option selected="selected">Chinese</option>
			<option>Germany</option>
			<option selected="selected">English</option>
		  </select>
		  <input type="checkbox" name="favorites" value="Basketball" id="ch1"/>
		  <input type="checkbox" name="favorites" value="tennis" checked="checked" id="ch2"/>
		  <input type="radio" name="gender" value="man" checked="checked" id="r1"/>
		  <input type="radio" name="gender" value="woman" id="r2"/>
		</form>		
      </body> 
</html>
`;
let document = new JSDOM(htmlStr, {
    url: 'http://127.0.0.1:8520',
    //referrer: '',
    contentType: 'text/html',
    includeNodeLocations: true,
    storageQuota: 10000000
});
// $ = require('C:\\Users\\yu\\node_global\\node_modules\\jquery')(document.window);
$ = require('jquery')(document.window);
// 序列化成表单形式
console.log($('form#container').serialize())
// 序列化成JSON数组形式
console.log($('form#container').serializeArray())
// js对象序列化成表单形式
console.log($.param({name: "codeplayer", age: 18, uid: [1, 2, 3]}))

$.ajax({
    url: '/getPersonInfo?page=1&id=3',
    type: 'POST',
    async: true,
    cache: false,
    // jQuery会自动将对象数据转换为 'name=codeplayer&age=18&uid=1&uid=2&uid=3'
    data: {name: "codeplayer", age: 18, uid: [1, 2, 3]}
    // 请求成功时执行
    , success: function (data, textStatus, jqXHR) {
        console.log('响应头数据:' + jqXHR.getAllResponseHeaders())
        console.log('响应体数据:', data)
        console.log('状态描述:%s,状态码:%s', textStatus, jqXHR.status)
        console.log('响应的原始文本:', jqXHR.responseText)
    }
    // 请求失败时执行
    , error: function (jqXHR, textStatus, errorMsg) {
        // jqXHR 是经过jQuery封装的XMLHttpRequest对象
        // textStatus 可能为： null、'timeout'、'error'、'abort'或'parsererror'
        // errorMsg 可能为： 'Not Found'、'Internal Server Error'等
        alert('请求失败：' + errorMsg);
    }
});
// contentType默认为application/x-www-form-urlencoded
// jQuery自动将对象拼接到url后面,/getPersonInfo?name=liang yudong&age=22
// 最后一个参数表示从服务器返回的预期的数据类型,默认为json
$.get('/getPersonInfo', {name: "liang yudong", age: 22}, function (data, textStatus, jqXHR) {
    console.log('响应头数据:' + jqXHR.getAllResponseHeaders())
    console.log('响应体数据:', data)
    console.log('状态描述:%s,状态码:%s', textStatus, jqXHR.status)
    console.log('响应的原始文本:', jqXHR.responseText)
}, 'xml');
$.getJSON('/getPersonInfo', {}, function (data, textStatus, jqXHR) {
    console.log('响应头数据:' + jqXHR.getAllResponseHeaders())
    console.log('响应体数据:', data)
    console.log('状态描述:%s,状态码:%s', textStatus, jqXHR.status)
    console.log('响应的原始文本:', jqXHR.responseText)
});
$.post("/getPersonInfo", {name: "Jack", age: 25}, function (data, textStatus, jqXHR) {
    console.log('响应头数据:' + jqXHR.getAllResponseHeaders())
    console.log('响应体数据:', data)
    console.log('状态描述:%s,状态码:%s', textStatus, jqXHR.status)
    console.log('响应的原始文本:', jqXHR.responseText)
}, 'json');

// 为以后要用到的Ajax请求设置默认的值
$.ajaxSetup({
    global: true
})
// 在每个请求之前被调用
$.ajaxPrefilter((options, originalOptions, jqXHR) => {
    console.log('ajaxPrefilter', options, originalOptions, jqXHR)
});
// AjaxAjax请求发送之前调用
$(document).ajaxSend((event, xhr, ajaxOptions) => {
    console.log('ajaxSend', event, xhr, ajaxOptions)
})
// Ajax请求完成时调用
$(document).ajaxComplete((event, xhr, ajaxOptions) => {
    console.log('ajaxComplete', event, xhr, ajaxOptions)
})
// Ajax请求成功时调用
$(document).ajaxSuccess((event, xhr, ajaxOptions) => {
    console.log('ajaxSuccess', event, xhr, ajaxOptions)
})
// Ajax请求出错时调用
$(document).ajaxError((event, xhr, ajaxOptions, error) => {
    console.error('ajaxError', event, xhr, ajaxOptions, error)
})
$.extend({
    postForObject: (reqUrl, reqJsonObj = {}, contentType = 'json') => {
        let reqStr;
        if (contentType == 'json') {
            reqStr = JSON.stringify(reqJsonObj);
            contentType = 'application/json'
        } else if (contentType == 'form') {
            reqStr = $.param(reqJsonObj);
            contentType = 'application/x-www-form-urlencoded'
        } else {
            throw new Error('contentType只能是json(默认)或者是form(表单格式)');
        }
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: reqUrl,
                type: 'POST',
                data: reqStr,
                contentType: contentType,
                dataType: 'json',
                success: function (data, textStatus, jqXHR) {
                    resolve(data, textStatus, jqXHR)
                },
                error: function (jqXHR, textStatus, errorMsg) {
                    reject(jqXHR, textStatus, errorMsg)
                }
            });
        })
    }
});
$.postForObject('http://localhost:8520/greeting', {name: 'liangyudong', company: 'bluemoon'})
    .then((data) => {
        console.log('success:', data)
    })
    .catch((jqXHR) => {
        console.error('error:', jqXHR)
    })
    .finally(() => {
        console.log('end')
    })