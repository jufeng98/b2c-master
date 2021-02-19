/**
 * Created by yudong on 2017/6/20 0020.
 *
 *使用 window.open() 方法既可以导航到一个特定的 URL，也可以打开一个新的浏览器窗口。这个方法可以接收 4 个参数：要加载的 URL、窗
 * 口目标、一个特性字符串以及一个表示新页面是否取代浏览器历史记录中当前加载页面的布尔值。通常只须传递第一个参数，最后一个参数只
 * 在不打开新窗口的情况下使用。
 如果给 window.open() 传递的第二个参数并不是一个已经存在的窗口或框架，那么该方法就会根据在第三个参数位置上传入的字符串创建一个
 新窗口或新标签页。如果没有传入第三个参数，那么就会打开一个带有全部默认设置（工具栏、地址栏和状态栏等）的新浏览器窗口（或者打开
 一个新标签页——根据浏览器设置）。在不打开新窗口的情况下，会忽略第三个参数。
 window.open() 方法会返回一个指向新窗口的引用。引用的对象与其他 window 对象大致相似，但我们可以对其进行更多控制。
 新创建的 window 对象有一个 opener 属性，其中保存着打开它的原始窗口对象。这个属性只在弹出窗口中的最外层 window 对象（ top）中
 有定义，而且指向调用 window.open() 的窗口或框架。
 */
var x = typeof window.screenLeft == "number" ? window.screenLeft : window.screenX;
var y = typeof window.screenTop == "number" ? window.screenTop : window.screenY;
console.log(x);
console.log(y);
console.log(window.innerWidth);
console.log(window.innerHeight);
console.log(window.outerWidth);
console.log(window.outerHeight);

var pageWidth = window.innerWidth,
    pageHeight = window.innerHeight;
if (typeof pageWidth != "number") {
    if (document.compatMode == "CSS1Compat") {
        pageWidth = document.documentElement.clientWidth;
        pageHeight = document.documentElement.clientHeight;
    } else {
        pageWidth = document.body.clientWidth;
        pageHeight = document.body.clientHeight;
    }
}
console.log(pageWidth);
console.log(pageHeight);
//等同于< a href="http://www.baidu.com" target="iaframe"></a>
window.open("http://www.baidu.com", "iaframe");
window.alert("hello world");
window.confirm("Are you sure?");//返回boolean值
window.prompt("what's your name?", "jack");//返回null或者字符串