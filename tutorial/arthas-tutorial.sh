#!/bin/bash
# arthas在线教程文档
https://arthas.aliyun.com/doc/

# 在线安装
curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar
# 如果下载速度比较慢，可以使用aliyun的镜像
java -jar arthas-boot.jar --repo-mirror aliyun --use-http

# 调用静态方法
ognl '@java.lang.System@out.println("hello world")'
ognl '@java.lang.Math@random()'
ognl "@java.lang.Thread@currentThread()"
ognl "@java.lang.Thread@currentThread().getContextClassLoader()"

# 获取类的静态字段
ognl '@java.lang.System@out'
getstatic java.lang.System out

#执行多行表达式，赋值给临时变量，返回一个List
ognl '#value1=@System@getProperty("java.home"), #value2=@System@getProperty("java.runtime.name"), {#value1, #value2}'

# 可调用被spring管理的任意bean的任意方法
ognl "#value1=@cn.com.bluemoon.mall.common.utils.AppContextHelper@applicationContext.getBean('promotionCouponBaseService').getByActId(new java.math.BigInteger('16032219530215768471')),
#value2=@com.alibaba.fastjson.JSONObject@toJSONString(#value1),
{#value2}"

# 获取加载该类的classloader的hash码
sc -d org.javamaster.b2c.swagger2.Swagger2Application
# 指定使用的classloader的hash码
ognl -c 439f5b3d "#value1=@org.javamaster.b2c.swagger2.Swagger2Application@context.getBean('loginServiceImpl').login(new org.javamaster.b2c.swagger2.model.UserReqVo(),'1'),
#value2=new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(#value1),
{#value2}"

# 若应用没有暴露context,可以用此命令记录context
tt -t -n 1 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter invokeHandlerMethod
# 拿到context
tt -i 1000 -w "target.getApplicationContext()"

# 若应用引入了dubbo,则可以这样拿到context
ognl "#context=@com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory@contexts.iterator.next,
#value1=#context.getBean('loginServiceImpl').login(new org.javamaster.b2c.swagger2.model.UserReqVo(),'1')"

# 查看占用CPU的线程
thread -n 3

# 查看JVM已加载的类信息
sc org.javamaster.b2c.swagger2.service.impl*

# 打印类的详细信息
sc -d -f org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl

# 查看已加载类的方法信息
sm org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl

# 查看方法的详细信息
sm -d org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl

# 反编译class文件
jad org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl

# 查看类加载器
classloader -l
# 使用ClassLoader去加载类
classloader -c 439f5b3d --load /tmp/LoginServiceImpl.class

# 本地编译好改动的class文件,上传到服务器重新加载(局限:不允许新增加field/method)
# 和jad/watch/trace/monitor/tt等命令会冲突，执行完redefine之后，如果再执行前面
# 提到的命令，则会把redefine的字节码重置
redefine /tmp/LoginServiceImpl.class

# 方法执行监控
monitor -c 5 org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login
# 方法执行堆栈耗时
trace -E org.javamaster.b2c.swagger2.controller.LoginController|org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login

# 观察方法调用前入参
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login {params} -x 2 -b
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login {params[0].username} -x 2 -b
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login "{params}" -x 2 -b
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login "{params[0].username}" -x 2 -b
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login "params" -x 2 -b
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login "params[0].username" -x 2 -b
# 对于重载方法,指定过滤条件
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login {params} "params.length==2" -x 2 -b
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login {params} "params.length==2 && params[1] instanceof String" -x 2 -b
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login {params} "params.length==2 && params[1] instanceof Integer" -x 2 -b
# 观察方法调用后入参
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login {params} -x 2 -s

# 观察方法调用后返回值
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login {returnObj} -x 2 -s

# 同时观察方法调用前后的入参和返回值
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login {params,returnObj} -x 2 -b -s

# 观察方法调用前对象的属性值
watch org.javamaster.b2c.swagger2.controller.LoginController login {target} -x 3 -b
# 观察方法调用前对象的具体某个属性值
watch org.javamaster.b2c.swagger2.controller.LoginController login {target.loginService.enabled} -x 3 -b

# ognl方式:查看第一个参数
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login "params[0]"
# ognl方式:按username方式投影
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login "params[0].{#this.username}"
# ognl方式:按条件过滤
watch org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login "params[0].{? #this.username=='root' }" -x -2

# 输出当前方法被调用的调用路径
stack org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login
# 按条件过滤
stack org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login "params[0].username=='root'"

# 记录下指定方法每次调用的入参和返回信息
tt -t -n 3 org.javamaster.b2c.swagger2.service.impl.LoginServiceImpl login
# 检索调用记录
tt -i 1000
# tt 命令由于保存了当时调用的所有现场信息，所以可重新触发方法调用
tt -i 1000 -p
