nui.parse();
function search() {
    var form1 = new nui.Form("form1");
    var data = form1.getData(true, true);
    var grid1 = nui.get("datagrid1");
    grid1.load(data);
}

function addEnterprise() {
    nui.open({
        url: "nui-test-add.html",
        showMaxButton: false,
        title: "洗涤套餐管理>>新增",
        width: '70%',
        height: '70%',
        ondestroy: function (action) {
            if (action == "success") {
                search();
            }
        }
    });
}

function search2() {
    var form2 = new nui.Form("form2");
    var data = form2.getData(true, true);
    var grid2 = nui.get("datagrid2");
    grid2.load(data);
}

function selectProducts() {
    var grid2 = nui.get("datagrid2");
    var rows = grid2.getSelecteds();
    var data = {criteria: rows};
    var jsonData = nui.encode(rows);
    var grid1 = nui.get("datagrid1");
    grid1.setData(rows);
}