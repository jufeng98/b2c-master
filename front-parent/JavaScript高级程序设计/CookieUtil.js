var CookieUtil = {
    get: function (name){
        let cookieName = encodeURIComponent(name) + "=",
            cookieStart = document.cookie.indexOf(cookieName),
            cookieValue = null;
        if (cookieStart > -1){
            let cookieEnd = document.cookie.indexOf(";", cookieStart);
            if (cookieEnd === -1){
                cookieEnd = document.cookie.length;
            }
            cookieValue = decodeURIComponent(document.cookie.substring(cookieStart
                + cookieName.length, cookieEnd));
        }
        return cookieValue;
    },
    set: function (name, value, expires, path, domain, secure) {
        let cookieText = encodeURIComponent(name) + "=" +
            encodeURIComponent(value);
        if (expires instanceof Date) {
            cookieText += "; expires=" + expires.toGMTString();
        }
        if (path) {
            cookieText += "; path=" + path;
        }if (domain) {
            cookieText += "; domain=" + domain;
        }
        if (secure) {
            cookieText += "; secure";
        }
        document.cookie = cookieText;
    },
    unset: function (name, path, domain, secure){
        this.set(name, "", new Date(0), path, domain, secure);
    }
};

//设置 cookie
CookieUtil.set("name", "Nicholas");
CookieUtil.set("book", "Professional JavaScript");
//读取 cookie 的值
alert(CookieUtil.get("name")); //"Nicholas"
alert(CookieUtil.get("book")); //"Professional JavaScript"
//删除 cookie
CookieUtil.unset("name");
CookieUtil.unset("book");
//设置 cookie，包括它的路径、域、失效日期
CookieUtil.set("name", "Nicholas", "/books/projs/", "www.wrox.com",
    new Date("January 1, 2010"));
//删除刚刚设置的 cookie
CookieUtil.unset("name", "/books/projs/", "www.wrox.com");
//设置安全的 cookie
CookieUtil.set("name", "Nicholas", null, null, null, true);

var SubCookieUtil = {
    set: function (name, subName, value, expires, path, domain, secure) {
        var subcookies = this.getAll(name) || {};subcookies[subName] = value;
        this.setAll(name, subcookies, expires, path, domain, secure);
    },
    setAll: function(name, subcookies, expires, path, domain, secure){
        var cookieText = encodeURIComponent(name) + "=",
            subcookieParts = new Array(),
            subName;
        for (subName in subcookies){
            if (subName.length > 0 && subcookies.hasOwnProperty(subName)){
                subcookieParts.push(encodeURIComponent(subName) + "=" +
                    encodeURIComponent(subcookies[subName]));
            }
        }
        if (cookieParts.length > 0){
            cookieText += subcookieParts.join("&");
            if (expires instanceof Date) {
                cookieText += "; expires=" + expires.toGMTString();
            }
            if (path) {
                cookieText += "; path=" + path;
            }
            if (domain) {
                cookieText += "; domain=" + domain;
            }
            if (secure) {
                cookieText += "; secure";
            }
        } else {
            cookieText += "; expires=" + (new Date(0)).toGMTString();
        }
        document.cookie = cookieText;
    },
    unset: function (name, subName, path, domain, secure){
        var subcookies = this.getAll(name);
        if (subcookies){
            delete subcookies[subName];
            this.setAll(name, subcookies, null, path, domain, secure);
        }
    },
    unsetAll: function(name, path, domain, secure){
        this.setAll(name, null, new Date(0), path, domain, secure);
    },
    get: function (name, subName){
        var subCookies = this.getAll(name);
        if (subCookies){
            return subCookies[subName];
        } else {
            return null;
        }
    },
    getAll: function(name){
        var cookieName = encodeURIComponent(name) + "=",
            cookieStart = document.cookie.indexOf(cookieName),
            cookieValue = null,
            cookieEnd,
            subCookies,
            i,
            parts,
            result = {};
        if (cookieStart > -1){
            cookieEnd = document.cookie.indexOf(";", cookieStart);
            if (cookieEnd == -1){
                cookieEnd = document.cookie.length;
            }
            cookieValue = document.cookie.substring(cookieStart +cookieName.length, cookieEnd);
            if (cookieValue.length > 0){
                subCookies = cookieValue.split("&");
                for (i=0, len=subCookies.length; i < len; i++){
                    parts = subCookies[i].split("=");
                    result[decodeURIComponent(parts[0])] =
                        decodeURIComponent(parts[1]);
                }
                return result;
            }
        }
        return null;
    },
//省略了更多代码
};
//假设 document.cookie=data=name=Nicholas&book=Professional%20JavaScript
//取得全部子 cookie
var data = SubCookieUtil.getAll("data");
alert(data.name); //"Nicholas"
alert(data.book); //"Professional JavaScript"
//逐个获取子 cookie
alert(SubCookieUtil.get("data", "name")); //"Nicholas"
alert(SubCookieUtil.get("data", "book")); //"Professional JavaScript"