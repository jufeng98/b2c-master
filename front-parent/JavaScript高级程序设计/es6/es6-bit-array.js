// ArrayBuffer对象代表储存二进制数据的一段内存，它不能直接读写，只能通过视图（TypedArray视图和DataView视图)来读写，视图的作用
// 是以指定格式解读二进制数据。
let buf = new ArrayBuffer(32);
// DataView是复合视图
let dv = new DataView(buf)
dv.setInt8(0, 48)
dv.setInt8(1, 49)
console.log(dv.getInt8(0))
// 默认情况下，DataView的get方法使用大端字节序解读数据，如果需要使用小端字节序解读，必须在get方法的第二个参数指定true
console.log(dv.getInt32(0).toString(2))
// TypedArray视图一共包括9种类型，每一种视图都是一种构造函数。
// Int8Array：8位有符号整数，长度1个字节。
// Uint8Array：8位无符号整数，长度1个字节。
// Uint8ClampedArray：8位无符号整数，长度1个字节，溢出处理不同。
// Int16Array：16位有符号整数，长度2个字节。
// Uint16Array：16位无符号整数，长度2个字节。
// Int32Array：32位有符号整数，长度4个字节。
// Uint32Array：32位无符号整数，长度4个字节。
// Float32Array：32位浮点数，长度4个字节。
// Float64Array：64位浮点数，长度8个字节。
let x1 = new Int32Array(buf);
// 按照小端在前的顺序读取,默认本机操作系统设定的字节序读写数据
console.log(x1[0])
console.log(x1.length)
console.log(x1.byteLength)
// 除了slice方法，ArrayBuffer对象不提供任何直接读写内存的方法，只允许在其上方建立视图，然后通过视图读写。
let newBuf = buf.slice(0, 4);
let x2 = new Int32Array(newBuf);
console.log(x2[0])

let ui8 = Uint8Array.of(0, 1, 2);
for (let byte of ui8) {
    console.log(byte.toString(2));
}

// XMLHttpRequest第二版XHR2允许服务器返回二进制数据，这时分成两种情况。如果明确知道返回的二进制数据类型，可以把返回类型
// （responseType）设为arraybuffer；如果不知道，就设为blob
// var xhr = new XMLHttpRequest();
// xhr.open('GET', '');
// xhr.responseType = 'arraybuffer';
//
// xhr.onload = function () {
//     let arrayBuffer = xhr.response;
//     // ···
// };
//
// xhr.send();