// $( function ) 是 $(document).ready( function ) 的简写形式
$(function () {
    // 在这里编写我们希望在DOM准备就绪后执行的代码

    $("#uid"); // 选取id属性为"uid"的单个元素
    $("p"); // 选取所有的p元素
    $(".test"); // 选择所有带有CSS类名"test"的元素
    $("[name=books]"); // 选择所有name属性为"books"的元素

    // 多个选择器以空格或指定符号隔开，将匹配与前者具有指定关系的最后一个选择器所表示的元素
    $("#uid span"); // 选择id为"uid"的元素的所有后代span元素
    $("p > span"); // 选择p元素的所有子代span元素
    $("div + p"); // 选择div元素后面紧邻的同辈p元素
    $("div p span"); // 选择div元素的所有后代p元素的后代span元素


    // 多个选择器之间没有空格，将匹配同时满足这些选择器条件的元素
    $("p#uid"); // 选择id属性为"uid"的p元素
    $("div.foo"); // 选择所有带有CSS类名"foo"的div元素
    $(".foo.bar"); // 选择所有同时带有CSS类名"foo"和"bar"的元素
    $("input[name=books][id]"); // 选择所有name属性为"books"并且具备id属性的元素

    // jQuery特有的选择器，当然也可以和其他选择器任意组合使用
    $(":checkbox"); // 选取所有的checkbox元素
    $(":text"); // 选取所有type为text的input元素
    $(":password"); // 选取所有type为password的input元素
    $(":checked"); // 选取所有选中的radio、checkbox、option元素
    $(":selected"); // 选取所有选中的option元素
    $(":input"); // 选取所有的表单控件元素(所有input、textarea、select、button元素)

    // 以下方法都返回一个新的jQuery对象，他们包含筛选到的元素
    $("ul li").eq(1); // 选取ul li中匹配的索引顺序为1的元素(也就是第2个li元素)
    $("ul li").first(); // 选取ul li中匹配的第一个元素
    $("ul li").last(); // 选取ul li中匹配的最后一个元素
    $("ul li").slice(1, 4); // 选取第2 ~ 4个元素
    $("ul li").filter(":even"); // 选取ul li中所有奇数顺序的元素
    $("div").find("p"); // 选取所有div元素的所有后代p元素
    $("div").children(); // 选取所有div元素的所有子代元素
    $("div").children("p"); // 选取所有div元素的所有子代p元素
    $("span").parent(); // 选取所有span元素的父元素
    $("span").parent(".foo.bar"); // 选取所有span元素的带有CSS类名"foo"和"bar"的父元素
    $("#uid").prev(); // 选取id为uid的元素之前紧邻的同辈元素
    $("#uid").next(); // 选取id为uid的元素之后紧邻的同辈元素

    // 返回一个匹配id为"username"的元素的jQuery对象
    var uid = $("#username");
    // 没有传入value参数，返回第一个匹配元素的value属性值
    var value = uid.val();
    // 传入了value参数，设置所有匹配元素的value值为"CodePlayer"
    uid.val("CodePlayer");
    // 返回匹配所有包含CSS类名"foo"的div元素的jQuery对象
    var div = $("div.foo");
    // 没有传入value参数，返回第一个匹配元素的value元素
    var fontSize = div.css("font-size");
    // 传入了value参数，设置所有匹配元素的font-size样式为"14px"
    div.css("font-size", "14px");

    var $lis = $("ul li"); // 匹配ul元素的所有后代li元素
    var className = $lis.attr("class"); // 只获取第一个匹配的li元素的class属性
    $lis.attr("class", "codeplayer"); // 将所有匹配的li元素的class属性设为"codeplayer"

    // jQuery的链式编程风格
    $("div").find("ul").addClass("menu").children().css("margin", 0).hide();

    // 以下是上述代码的分解描述
    $("div") // 返回一个匹配所有div元素的jQuery对象
        .find("ul") // 返回匹配这些div元素中的所有后代ul元素的jQuery对象
        .addClass("menu") // 为这些ul元素添加CSS类名"menu"，并返回当前对象本身
        .children() // 返回匹配这些ul元素中的所有子代元素的jQuery对象
        .css("margin", 0) // 为这些子代元素设置css样式"margin: 0"，并返回当前对象本身
        .hide(); // 隐藏这些子代元素，并返回当前对象本身

    // 没有标签为abc的DOM元素，$("abc")是一个空的jQuery对象，调用其find()方法将返回一个新的jQuery空对象
    var a = $("abc").find("p");

    // 如果不存在id为notFound的元素，$("#notFound")是一个空的jQuery对象，获取其id属性，将返回undefined。
    var b = $("#notFound").attr("id");

    // 如果不存在id为notFound的元素，$("#notFound")是一个空的jQuery对象，获取其高度值，将返回null。
    var c = $("#notFound").height();

    // 如果不存在id为uname的元素，$("#uname")是一个空的jQuery对象，设置其value值，将忽略该设置操作，并返回该空对象本身
    var d = $("#uname").val("xxxxx");

    // selector 表示具体的选择器

    $("selector").val(); // 获取第一个匹配元素的value值(一般用于表单控件)
    $("selector").val("Hello"); // 设置所有匹配元素的value值为"Hello"

    $("selector").html(); // 获取第一个匹配元素的innerHTML值
    $("selector").html("Hello"); // 设置所有匹配元素的innerHTML值为"Hello"

    $("selector").text(); // 获取第一个匹配元素的innerText值(jQuery已进行兼容处理)
    $("selector").text("Hello"); // 设置所有匹配元素的innerText值为"Hello"

    $("selector").attr("class"); // 获取第一个匹配元素class属性
    $("selector").attr("class", "code"); // 设置所有匹配元素的class属性为"code"
    $("selector").removeAttr("class"); // 移除所有匹配元素的class属性

    $("selector").prop("checked"); // 获取第一个匹配元素的checked属性值
    $("selector").prop("checked", true); // 设置所有匹配元素的checked属性值为true(意即选中所有匹配的复选框或单选框)
    $("selector").removeProp("className"); // 移除所有匹配元素的className属性

    $('tr').each(function () {
        console.log(arguments[1]);
    });
});