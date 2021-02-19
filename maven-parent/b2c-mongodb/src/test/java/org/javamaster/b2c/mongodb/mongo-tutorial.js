// show dbs

// 若此时sakila数据库不存在,则会自动创建,也可启用严格模式来禁止此类行为
// use sakila

// 查看数据库相关信息
// db.stats()

// db.createCollection("vendors",{size:20000})
// db.vendors.renameCollection("users")

// 列出常用方法
// db.help()

// show collections

// 查看集合相关信息
// db.vendors.stats()

// 列出常用方法
// db.vendors.help()

// db.vendors.insert({"vend_id":"BRS01","vend_name":"Bears R US"})

// 是对insert和update的封装,如果待保存的对象没有_id字段,则会添加该字段并执行insert,否则执行update
// db.vendors.save({"vend_id":"BRS02","vend_name":"Bears R UK"})

// db.vendors.count()

// db.vendors.find({"vend_id":"BRS02"})
// db.vendors.find({"vend_id":"BRS02"}).explain()
// 模糊匹配
// /^TW/ 等价于sql %TW
// $gte 等价于sql >=
// $lte 等价于sql <=
// $lt 等价于sql <
// $gt 等价于sql >
// $in 等价于sql in
// $nin 等价于sql not in
// $ne 等价于sql !=
// $nor 等价于sql nor
// $or 等价于sql or
// $and 等价于sql and
// skip和limit可用于分页
// {age:{$gte:0,$lte:30}}等价于sql  age>=0 and age<=30
// 创建索引
// db.vendors.ensureIndex(vend_id:1)
// 查看索引
// db.vendors.getIndexes()

// 更新,添加新属性
// db.vendors.update({"vend_id":"BRS02"}, {"$set":{"vend_country":"CHINA"}})
// db.vendors.update({"vend_id":"BRS02"}, {"$set":{"favorites":{"movies":["Rose","Jack"]}}})
// 更新,去除属性
// db.vendors.update({"vend_id":"BRS02"}, {"$unset":{"vend_country":"CHINA"}})
// 更新,添加内容到已有属性中,第四个参数代表多项更新
// db.vendors.update({"vend_id":"BRS02"}, {"$addToSet":{"favorites.movies":"HiJack"}}, false, true)
// $inc {$inc:{field:value}} 对一个数字字段的某个field增加value
// $set {$set:{field:value}} 把文档中某个字段field的值设为value
// $unset {$unset:{field:1}} 删除某个字段field
// $push {$push:{field:value}} 把value追加到field里。注：field只能是数组类型，如果field不存在，会自动插入一个数组类型
// $pushAll {$pushAll:{field:value_array}} 用法同$push一样，只是$pushAll可以一次追加多个值到一个数组字段内。
// $addToSet {$addToSet:{field:value}} 加一个值到数组内，而且只有当这个值在数组中不存在时才增加。
// $pop 删除数组内第一个值：{$pop:{field:-1}}、删除数组内最后一个值：{$pop:{field:1}} 用于删除数组内的一个值
// $pull {$pull:{field:_value}} 从数组field内删除一个等于_value的值
// $pullAll {$pullAll:value_array} 用法同$pull一样，可以一次性删除数组内的多个值。
// $rename {$rename:{old_field_name:new_field_name}} 对字段进行重命名
// $currentDate {$currentDate:{date_field:1}} 更新时间字段值为当前时间
// $max {$max:{field:value}} 当value大于field原来的值时,更新field的值为value
// $min 同理
// $add、$subtract、$multiply、$divide 加减乘除
// { $add: [ "$date", 3*24*60*60*1000 ] }
// { $subtract: [ new Date(), "$date" ] }
// { $multiply: [ "$price", "$quantity" ] }
// { $divide: [ "$hours", 8 ] }
// $project
// db.sales.aggregate( [ { $project : { _id: 0, item : 1 , price : 1 } } ] ) 只显示出 item、price列
// db.sales.aggregate( [ { $project : { _id: 0, item : 1 , price : 1, qty: "$quantity" } } ] ) 设置别名

// 清空集合
// db.vendors.remove()
// 删除符合条件的记录
// db.vendors.remove({"vend_id":"BRS02"})

// 删除集合
// db.vendors.drop()