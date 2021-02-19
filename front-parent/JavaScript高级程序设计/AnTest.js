var strArr = "广州,广州,广州,深圳,深圳";
var arr = strArr.split(",");
arr = [...new Set(arr)]
console.log(arr.join(","))