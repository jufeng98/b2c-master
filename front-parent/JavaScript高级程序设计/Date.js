/**
 * Created by yudong on 2017/6/16 0016.

 Date 类型是在早期 Java 中的 java.util.Date 类基础上构建的。为此， Date类型使用自 UTC（ Coordinated Universal Time，国际协调
 时间） 1970 年 1 月 1 日午夜（零时）开始经过的毫秒数来保存日期。
 Date.parse() 方法接收一个表示日期的字符串参数，然后尝试根据这个字符串返回相应日期的毫秒数。 ECMA-262 没有定义 Date.parse() 应
 该支持哪种日期格式，因此这个方法的行为因实现而异，而且通常是因地区而异。如果传入 Date.parse() 方法的字符串不能表示日期，那么它
 会返回 NaN。
 Date.UTC() 方法同样也返回表示日期的毫秒数，但它与 Date.parse() 在构建值时使用不同的信息。Date.UTC() 的参数分别是年份、基于 0
 的月份（一月是 0，二月是 1，以此类推）、月中的哪一天（ 1 到 31）、小时数（ 0 到 23）、分钟、秒以及毫秒数。在这些参数中，只有前
 两个参数（年和月）是必需的。如果没有提供月中的天数，则假设天数为 1；如果省略其他参数，则统统假设为 0。
 Data.now() 方法，返回表示调用这个方法时的日期和时间的毫秒数。
 Date 的 valueOf() 方法，则根本不返回字符串，而是返回日期的毫秒表示。
 Date 类型还有一些专门用于将日期格式化为字符串的方法，这些方法如下:
  toDateString() ——以特定于实现的格式显示星期几、月、日和年；
  toTimeString() ——以特定于实现的格式显示时、分、秒和时区；
  toLocaleDateString() ——以特定于地区的格式显示星期几、月、日和年；
  toLocaleTimeString() ——以特定于实现的格式显示时、分、秒；
  toUTCString() ——以特定于实现的格式完整的 UTC 日期。
 与 toLocaleString() 和 toString() 方法一样，以上这些字符串格式方法的输出也是因浏览器而异的，因此没有哪一个方法能够用来在用户界
 面中显示一致的日期信息。ECMAScript 推荐现在编写的代码一律使用 toUTCString() 方法。
 getTime() 返回表示日期的毫秒数；与valueOf() 方法返回的值相同
 setTime(毫秒) 以毫秒数设置日期，会改变整个日期
 getFullYear() 取得4位数的年份（如2007而非仅07）
 getUTCFullYear() 返回UTC日期的4位数年份
 setFullYear(年) 设置日期的年份。传入的年份值必须是4位数字（如2007而非仅07）
 setUTCFullYear(年) 设置UTC日期的年份。传入的年份值必须是4位数字（如2007而非仅07）
 getMonth() 返回日期中的月份，其中0表示一月， 11表示十二月
 getUTCMonth() 返回UTC日期中的月份，其中0表示一月， 11表示十二月
 setMonth(月 ) 设置日期的月份。传入的月份值必须大于0，超过11则增加年份
 setUTCMonth(月 ) 设置UTC日期的月份。传入的月份值必须大于0，超过11则增加年份
 getDate() 返回日期月份中的天数（ 1到31）
 getUTCDate() 返回UTC日期月份中的天数（ 1到31）
 setDate(日 ) 设置日期月份中的天数。如果传入的值超过了该月中应有的天数，则增加月份
 setUTCDate(日 ) 设置UTC日期月份中的天数。如果传入的值超过了该月中应有的天数，则增加月份
 getDay() 返回日期中星期的星期几（其中0表示星期日， 6表示星期六）
 getUTCDay() 返回UTC日期中星期的星期几（其中0表示星期日， 6表示星期六）
 getHours() 返回日期中的小时数（ 0到23）
 getUTCHours() 返回UTC日期中的小时数（ 0到23）
 setHours(时) 设置日期中的小时数。传入的值超过了23则增加月份中的天数
 setUTCHours(时) 设置UTC日期中的小时数。传入的值超过了23则增加月份中的天数
 getMinutes() 返回日期中的分钟数（ 0到59）
 getUTCMinutes() 返回UTC日期中的分钟数（ 0到59）
 setMinutes(分) 设置日期中的分钟数。传入的值超过59则增加小时数
 setUTCMinutes(分) 设置UTC日期中的分钟数。传入的值超过59则增加小时数
 getSeconds() 返回日期中的秒数（ 0到59）
 getUTCSeconds() 返回UTC日期中的秒数（ 0到59）
 setSeconds(秒) 设置日期中的秒数。传入的值超过了59会增加分钟数
 setUTCSeconds(秒) 设置UTC日期中的秒数。传入的值超过了59会增加分钟数
 getMilliseconds() 返回日期中的毫秒数
 getUTCMilliseconds() 返回UTC日期中的毫秒数
 setMilliseconds(毫秒) 设置日期中的毫秒数
 setUTCMilliseconds(毫秒) 设置UTC日期中的毫秒数
 getTimezoneOffset() 返回本地时间与UTC时间相差的分钟数。例如，美国东部标准时间返回300。在某地进入夏令时的情况下，这个值会有所变化
 */
var now = new Date();
console.log(now);

var someDate = new Date(Date.parse("May 25, 2004"));
console.log(someDate.toUTCString());

var y2k = new Date(Date.UTC(2000, 0));
console.log(y2k);

//注意,月份处一月是 0，二月是 1，以此类推 new Date(2005, 4, 5, 17, 55, 55);
var allFives = new Date(Date.UTC(2005, 4, 5, 23, 55, 55));
console.log(allFives.valueOf());
console.log(allFives.getTime());

console.log(Date.now());
console.log(now.getFullYear());
console.log(now.getMonth());
console.log(now.getDate());
console.log(now.getDay());

var date1 = new Date(2007, 0, 1); //"January 1, 2007"
var date2 = new Date(2007, 1, 1); //"February 1, 2007"
console.log(date1.valueOf());
console.log(date1 < date2); //true
console.log(date1 > date2); //false
