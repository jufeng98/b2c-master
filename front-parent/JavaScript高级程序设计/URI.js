/**
 * Created by yudong on 2017/6/16 0016.
 encodeURI() 和 encodeURIComponent() 方法可以对 URI（ Uniform Resource
 Identifiers，通用资源标识符）进行编码，以便发送给浏览器。有效的 URI 中不能包含某些字符，例如
 空格。而这两个 URI 编码方法就可以对 URI 进行编码，它们用特殊的 UTF-8 编码替换所有无效的字符，
 从而让浏览器能够接受和理解。
 encodeURI() 不会对本身属于 URI 的特殊字符进行编码，例如冒号、正斜杠、问号和井字号；
 而 encodeURIComponent() 则会对它发现的任何非标准字符进行编码。
 一 般 来 说 ， 我 们 使 用 encodeURIComponent() 方 法 的 时 候 要 比 使 用
 encodeURI() 更多，因为在实践中更常见的是对查询字符串参数而不是对基础 URI
 进行编码。
 与 encodeURI() 和 encodeURIComponent() 方法对应的两个方法分别是 decodeURI() 和
 decodeURIComponent() 。
 */
var uri = "http://www.wrox.com/illegal value.htm#start";

console.log(encodeURI(uri));

console.log(encodeURIComponent(uri));

function addURLParam(url, name, value) {
    url += (url.indexOf("?") == -1 ? "?" : "&");
    url += encodeURIComponent(name) + "=" + encodeURIComponent(value);
    return url;
}

