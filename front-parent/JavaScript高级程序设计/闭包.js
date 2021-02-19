/**
 * Created by yudong on 2017/6/19 0019.
 */
function createFunctions() {
    var result = new Array();
    for (var i = 0; i < 10; i++) {
        result[i] = function () {
            var temp = i;
            console.log(temp);
            return temp;
        };
    }
    return result;
}

createFunctions().forEach(function (item, index, arr) {
    console.log(item());
});


function createFunctions() {
    var result = new Array();
    for (var i = 0; i < 10; i++) {
        result[i] = function (num) {
            return function () {
                return num;
            };
        }(i);
    }
    return result;
}