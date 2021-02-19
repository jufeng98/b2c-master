/**
 * Created by yudong on 2017/6/20 0020.
 *
 history 对象保存着用户上网的历史记录，从窗口被打开的那一刻算起。因为 history 是 window对象的属性，因此每个浏览器窗口、每个标签
 页乃至每个框架，都有自己的 history 对象与特定的window 对象关联。
 使用 go() 方法可以在用户的历史记录中任意跳转，可以向后也可以向前。这个方法接受一个参数，表示向后或向前跳转的页面数的一个整数值。
 还可以使用两个简写方法 back() 和 forward() 来代替 go() 。顾名思义，这两个方法可以模仿浏览器的“后退”和“前进”按钮。
 history 对象还有一个 length 属性，保存着历史记录的数量。
 当页面的 URL 改变时，就会生成一条历史记录。在 IE8 及更高版本、 Opera、Firefox、 Safari 3 及更高版本以及 Chrome 中，这里所说的
 改变包括 URL 中 hash 的变化（因此，设置 location.hash 会在这些浏览器中生成一条新的历史记录）。
 */
//后退一页
history.go(-1);
//前进一页
history.go(1);
//前进两页
history.go(2);
//后退一页
history.back();
//前进一页
history.forward();
if (history.length == 0) {
    //这应该是用户打开窗口后的第一个页面
}