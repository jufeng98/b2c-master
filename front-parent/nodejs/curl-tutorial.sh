#!/usr/bin/env bash
#启动node.js服务器

#基本
curl http://localhost:8520/getPersonInfo

#只显示响应头信息
curl -I http://localhost:8520/getPersonInfo

#显示所有响应信息
curl -i http://localhost:8520/getPersonInfo

#重定向保存整个返回结果
curl http://localhost:8520/getPersonInfo >> /curl/res.txt

#只保存响应header信息
curl -D /curl/head.txt http://localhost:8520/getPersonInfo

#只保存响应体内容
curl -o /curl/res.txt http://localhost:8520/getPersonInfo

#下载文件
curl -o /curl/pic.png https://www.javamaster.com/cn/new/images/headfoot.png

#以服务器文件名下载文件到本地
curl -O https://www.javamaster.com/cn/new/images/headfoot.png

#断点续传
curl -C -O https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_86d58ae1.png

#测试网站是否正常
curl -o /dev/null -s -w %{http_code} http://localhost:8520/getPersonInfo

#保存返回的cookie
curl -c cookie.txt  http://localhost:8520/getPersonInfo

#读取保存的cookie
curl -b cookie.txt http://localhost:8520/getPersonInfo

#伪造用户代理
curl -A "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.0)" http://localhost:8520/getPersonInfo

#伪造refer
curl -e "http://localhost:8520" http://localhost:8520/getPersonInfo

#POST请求并带上请求体信息(省略 -X，则默认为 POST 方式)
curl -d "user=liangyudong&password=123456" http://localhost:8520/getPersonInfo

#POST form表单形式提交数据和上传文件,@本地文件名
curl -F user=liangyudong -F upload=@json.txt http://localhost:8520/getPersonInfo

#自定义请求头信息并带上请求体信息
curl -H "Content-Type:application/json" -H "Accept:text/plain" -d "{\"user\":\"liangyudong\"}" http://localhost:8520/getPersonInfo

#自定义请求头信息并从文件中读取请求体信息,@本地文件名
curl -H "Content-Type:application/json" -d @json.txt http://localhost:8520/getPersonInfo

#GET请求,-X指定请求方法
curl -X GET http://localhost:8520/getPersonInfo?user=liangyudong&password=123456

#获取跳转后的内容
 curl -L https://b2c.javamaster.com/portal/ordermanage/planeOrder/queryPlaneOrders

# 启动FtpServerTest的FTP服务器

#ftp查看文件
curl -u root:root ftp://localhost/
curl ftp://root:root@localhost/

# ftp下载文件
curl -O -u root:root ftp://localhost/curl-7.58.0.cab

# ftp上传文件
curl -T "pic.png" -u root:root ftp://localhost/