/**
 * Created by yudong on 2017/6/20 0020.
 *
 最早由Netscape Navigator 2.0引入的navigator对象，现在已经成为识别客户端浏览器的事实标准。
 属性或方法                    说明                                        IE      Firefox     Safari/Chrome      Opera
 appCodeName      浏览器的名称。通常都是Mozilla，即使在非Mozilla浏览器中也是如此 3.0+ 1.0+ 1.0+  7.0+
 appMinorVersion            次版本信息                                    4.0+  － －  9.5+
 appName                 完整的浏览器名称                                 3.0+ 1.0+ 1.0+  7.0+
 appVersion    浏览器的版本。一般不与实际的浏览器版本对应                   3.0+ 1.0+ 1.0+  7.0+
 buildID                浏览器编译版本                                    －  2.0+  －  －
 cookieEnabled         表示cookie是否启用                                4.0+ 1.0+ 1.0+  7.0+
 cpuClass           客户端计算机中使用的CPU类型（x86、68K、Alpha、PPC或Other）4.0+  －  －  －
 javaEnabled()   表示当前浏览器中是否启用了Java                           4.0+ 1.0+ 1.0+  7.0+
 language                 浏览器的主语言                                 －  1.0+ 1.0+  7.0+
 mimeTypes        在浏览器中注册的MIME类型数组                           4.0+ 1.0+ 1.0+  7.0+
 onLine           表示浏览器是否连接到了因特网                            4.0+ 1.0+  －  9.5+
 opsProfile      似乎早就不用了。查不到相关文档                             4.0+  －  －  －
 oscpu         客户端计算机的操作系统或使用的CPU                           －  1.0+  －  －
 platform             浏览器所在的系统平台                                4.0+ 1.0+ 1.0+  7.0+
 plugins          浏览器中安装的插件信息的数组                            4.0+ 1.0+ 1.0+  7.0+
 preference()            设置用户的首选项                                －  1.5+  －  －
 product             产品名称（如 Gecko）                                －  1.0+ 1.0+  －
 productSub    关于产品的次要信息（如Gecko的版本）                        －  1.0+ 1.0+  －
 register-ContentHandler()     针对特定的MIME类型将一个站点注册为处理程序   －  2.0+  －  －
 register-ProtocolHandler()    针对特定的协议将一个站点注册为处理程序       －  2.0  －  －
 securityPolicy  已经废弃。安全策略的名称。为了与Netscape Navigator 4向后兼容而保留下来  －  1.0+  －  －
 */
var client = function () {
    //呈现引擎
    var engine = {
        ie: 0,
        gecko: 0,
        webkit: 0,
        khtml: 0,
        opera: 0,
        //完整的版本号
        ver: null
    };
    //浏览器
    var browser = {
        //主要浏览器
        ie: 0,
        firefox: 0,
        safari: 0,
        konq: 0,
        opera: 0,
        chrome: 0,
        //具体的版本号
        ver: null
    };
    //平台、设备和操作系统
    var system = {
        win: false,
        mac: false,
        x11: false,
        //移动设备
        iphone: false,
        ipod: false,
        ipad: false,
        ios: false,
        android: false,
        nokiaN: false,
        winMobile: false,
        //游戏系统
        wii: false,
        ps: false
    };
    //检测呈现引擎和浏览器
    var ua = navigator.userAgent;
    if (window.opera) {
        engine.ver = browser.ver = window.opera.version();
        engine.opera = browser.opera = parseFloat(engine.ver);
    } else if (/AppleWebKit\/(\S+)/.test(ua)) {
        engine.ver = RegExp["$1"];
        engine.webkit = parseFloat(engine.ver);
        //确定是 Chrome 还是 Safari
        if (/Chrome\/(\S+)/.test(ua)) {
            browser.ver = RegExp["$1"];
            browser.chrome = parseFloat(browser.ver);
        } else if (/Version\/(\S+)/.test(ua)) {
            browser.ver = RegExp["$1"];
            browser.safari = parseFloat(browser.ver);
        } else {
            //近似地确定版本号
            var safariVersion = 1;
            if (engine.webkit < 100) {
                safariVersion = 1;
            } else if (engine.webkit < 312) {
                safariVersion = 1.2;
            } else if (engine.webkit < 412) {
                safariVersion = 1.3;
            } else {
                safariVersion = 2;
            }
            browser.safari = browser.ver = safariVersion;
        }
    } else if (/KHTML\/(\S+)/.test(ua) || /Konqueror\/([^;]+)/.test(ua)) {
        engine.ver = browser.ver = RegExp["$1"];
        engine.khtml = browser.konq = parseFloat(engine.ver);
    } else if (/rv:([^\)]+)\) Gecko\/\d{8}/.test(ua)) {
        engine.ver = RegExp["$1"];
        engine.gecko = parseFloat(engine.ver);
        //确定是不是 Firefox
        if (/Firefox\/(\S+)/.test(ua)) {
            browser.ver = RegExp["$1"];
            browser.firefox = parseFloat(browser.ver);
        }
    } else if (/MSIE ([^;]+)/.test(ua)) {
        engine.ver = browser.ver = RegExp["$1"];
        engine.ie = browser.ie = parseFloat(engine.ver);
    }
    //检测浏览器
    browser.ie = engine.ie;
    browser.opera = engine.opera;
    //检测平台
    var p = navigator.platform;
    system.win = p.indexOf("Win") == 0;
    system.mac = p.indexOf("Mac") == 0;
    system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
    //检测 Windows 操作系统
    if (system.win) {
        if (/Win(?:dows )?([^do]{2})\s?(\d+\.\d+)?/.test(ua)) {
            if (RegExp["$1"] == "NT") {
                switch (RegExp["$2"]) {
                    case "5.0":
                        system.win = "2000";
                        break;
                    case "5.1":
                        system.win = "XP";
                        break;
                    case "6.0":
                        system.win = "Vista";
                        break;
                    case "6.1":
                        system.win = "7";
                        break;
                    default:
                        system.win = "NT";
                        break;
                }
            } else if (RegExp["$1"] == "9x") {
                system.win = "ME";
            } else {
                system.win = RegExp["$1"];
            }
        }
    }
//移动设备
    system.iphone = ua.indexOf("iPhone") > -1;
    system.ipod = ua.indexOf("iPod") > -1;
    system.ipad = ua.indexOf("iPad") > -1;
    system.nokiaN = ua.indexOf("NokiaN") > -1;
    //windows mobile
    if (system.win == "CE") {
        system.winMobile = system.win;
    } else if (system.win == "Ph") {
        if (/Windows Phone OS (\d+.\d+)/.test(ua)) {
            ;
            system.win = "Phone";
            system.winMobile = parseFloat(RegExp["$1"]);
        }
    }
    //检测 iOS 版本
    if (system.mac && ua.indexOf("Mobile") > -1) {
        if (/CPU (?:iPhone )?OS (\d+_\d+)/.test(ua)) {
            system.ios = parseFloat(RegExp.$1.replace("_", "."));
        } else {
            system.ios = 2; //不能真正检测出来，所以只能猜测
        }
    }
    //检测 Android 版本
    if (/Android (\d+\.\d+)/.test(ua)) {
        system.android = parseFloat(RegExp.$1);
    }
    //游戏系统
    system.wii = ua.indexOf("Wii") > -1;
    system.ps = /playstation/i.test(ua);
    //返回这些对象
    return {
        engine: engine,
        browser: browser,
        system: system
    };
}();
console.log(client.browser);
console.log(client.engine);
console.log(client.system);