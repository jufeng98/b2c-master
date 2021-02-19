/**
 * Created by yudong on 2017/6/20 0020.
 *
 location 是最有用的 BOM 对象之一，它提供了与当前窗口中加载的文档有关的信息，还提供了一些导航功能。事实上， location 对象是很特
 别的一个对象，因为它既是 window 对象的属性，也是document 对象的属性；换句话说， window.location 和 document.location 引用的
 是同一个对象。
 属性名              例子                                                   说明
 hash             "#contents"               返回URL中的hash（ #号后跟零或多个字符），如果URL中不包含散列，则返回空字符串
 host           "www.wrox.com:80"                              返回服务器名称和端口号（如果有）
 hostname        "www.wrox.com"                                  返回不带端口号的服务器名称
 href          "http:/www.wrox.com"           返回当前加载页面的完整 URL。而location 对象的toString() 方法也返回这个值
 pathname        "/WileyCDA/"                                   返回URL中的目录和（或）文件名
 port               "8080"                      返回URL中指定的端口号。如果URL中不包含端口号，则这个属性返回空字符串
 protocol           "http:"                               返回页面使用的协议。通常是http: 或https:
 search         "?q=javascript"                          返回URL的查询字符串。这个字符串以问号开头

 每次修改 location 的属性（ hash 除外），页面都会以新 URL 重新加载。
 当通过上述任何一种方式修改 URL 之后，浏览器的历史记录中就会生成一条新记录，因此用户通过单击“后退”按钮都会导航到前一个页面。要禁
 用这种行为，可以使用 replace() 方法。这个方法只接受一个参数，即要导航到的 URL；结果虽然会导致浏览器位置改变，但不会在历史记录中
 生成新记录。在调用 replace() 方法之后，用户不能回到前一个页面

 与位置有关的最后一个方法是 reload() ，作用是重新加载当前显示的页面。如果调用 reload()时不传递任何参数，页面就会以最有效的方式
 重新加载。也就是说，如果页面自上次请求以来并没有改变过，页面就会从浏览器缓存中重新加载。如果要强制从服务器重新加载，则需要像下
 面这样为该方法传递参数 true。
 */
console.log(location.protocol);
console.log(location.search);
console.log(location.hash);

location.assign("http://www.wrox.com");
//下列两行代码与显式调用 assign() 方法的效果完全一样。
//location.href = "http://www.wrox.com";
//window.location = "http://www.wrox.com";

function getQueryStringArgs() {
    //取得查询字符串并去掉开头的问号
    var qs = (location.search.length > 0 ? location.search.substring(1) : ""),
        //保存数据的对象
        args = {},
        //取得每一项
        items = qs.length ? qs.split("&") : [],
        item = null,
        name = null,
        value = null,
        //在 for 循环中使用
        i = 0,
        len = items.length;
    //逐个将每一项添加到 args 对象中
    for (i = 0; i < len; i++) {
        item = items[i].split("=");
        name = decodeURIComponent(item[0]);
        value = decodeURIComponent(item[1]);
        if (name.length) {
            args[name] = value;
        }
    }
    return args;
}