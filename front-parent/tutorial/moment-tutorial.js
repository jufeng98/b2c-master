let moment = require('moment')
// 日期格式化
console.log(moment().format("YYYY年MM月DD日"))
console.log(moment(1411641720000).format('YYYY-MM-DD HH:mm:ss'))
console.log(moment().day(0).format('YYYY-MM-DD'))
moment().format('MMMM Do YYYY, h:mm:ss a'); // 六月 24日 2020, 10:27:02 晚上
moment().format('dddd');                    // 星期三
moment().format("MMM Do YY");               // 6月 24日 20
moment().format('YYYY [escaped] YYYY');     // 2020 escaped 2020
moment().format();                          // 2020-06-24T22:27:02+08:00

// 获取前一天日期
console.log(moment().subtract(1, 'days').format('YYYY-MM-DD'))
// 获取本周五日期
console.log(moment().weekday(5).format('YYYY-MM-DD'))
// 获取上周五日期
console.log(moment().weekday(-3).format('YYYY-MM-DD'))
// 获取上个月今天的日期
console.log(moment().subtract(1, 'months').format('YYYY-MM-DD'))
// 获取两个小时之后的时间
console.log(moment().add(2,'hours').format('YYYY-MM-DD HH:mm:ss'))

moment().subtract(10, 'days').calendar(); // 2020/06/14
moment().subtract(6, 'days').calendar();  // 上星期四22:27
moment().subtract(3, 'days').calendar();  // 上星期日22:27
moment().subtract(1, 'days').calendar();  // 昨天22:27
moment().calendar();                      // 今天22:27
moment().add(1, 'days').calendar();       // 明天22:27
moment().add(3, 'days').calendar();       // 下星期六22:27
moment().add(10, 'days').calendar();      // 2020/07/04

moment("20111031", "YYYYMMDD").fromNow(); // 9 年前
moment("20120620", "YYYYMMDD").fromNow(); // 8 年前
moment().startOf('day').fromNow();        // 1 天前
moment().endOf('day').fromNow();          // 2 小时内
moment().startOf('hour').fromNow();       // 27 分钟前

// 获取年份、月份、日期
var t14 = moment().year()
var t15 = moment().month() // 此处月份从0开始，当前月要+1
var t16 = moment().date()
console.log(`${t14 - 1}-${t15 + 1}-${t16}`)

