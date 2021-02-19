function search() {

    var div = $("#result");
    var form = new mini.Form("#form1");

    var data = form.getData();      //获取表单多个控件的数据
    var json = mini.encode(data);
    div.text(json);

    var div1 = $("#result1");
    var data1 = $("#form1").serializeArray();
    var json1 = JSON.stringify(data1);
    div1.text(json1);

}

function pop() {
    nui.open({
        url: "mini-test-pop.html",
        showMaxButton: false,
        title: "洗涤套餐管理>>新增",
        width: '70%',
        height: '70%',
        onload: function () {       //弹出页面加载完成
            var iframe = this.getIFrameEl();
            var data = {"action":"new"};
            //调用弹出页面方法进行初始化
            iframe.contentWindow.SetData(data);

        },
        ondestroy: function (action) {
            if (action == "success") {
                var iframe = this.getIFrameEl();
                //获取选中、编辑的结果
                var data = iframe.contentWindow.getData();
                data = mini.clone(data);    //必须。克隆数据。
                var dddd=window.newdata;
            }
        }
    });

}