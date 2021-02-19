/**
 * Created by yudong on 2017/6/19 0019.
 *
 * 模块模式（ module pattern）则是为单例创建私有变量和特权方法。所谓单例（ singleton），指的就是只有一个实例的对象。
 按照惯例， JavaScript 是以对象字面量的方式来创建单例对象的。
 简言之，如果必须创建一个对象并以某些数据对其进行初始化，同时还要公开一些能够访问这些私有数据的方法，那么就可以使用模块模式。有
 人进一步改进了模块模式，即在返回对象之前加入对其增强的代码。这种增强的模块模式适合那单例必须是某种类型的实例，同时还必须添加某些
 属性和（或）方法对其加以增强的情况。

 */
var singleton = function () {
    //私有变量和私有函数
    var privateVariable = 10;

    function privateFunction() {
        return false;
    }

    //特权/公有方法和属性
    return {
        publicProperty: true,
        publicMethod: function () {
            privateVariable++;
            return privateFunction();
        }
    };
}();

var singleton = function () {
    //私有变量和私有函数
    var privateVariable = 10;

    function privateFunction() {
        return false;
    }

    //创建对象
    var object = new Object();
    //添加特权/公有属性和方法
    object.publicProperty = true;
    object.publicMethod = function () {
        privateVariable++;
        return privateFunction();
    };
    //返回这个对象
    return object;
}();