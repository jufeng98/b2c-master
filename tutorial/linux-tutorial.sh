#!/bin/bash
# 查看当前在线用户
who

# 查看用户各类id信息
id

# 可以查得这个月份有登入主机者的身份
last

# 查看进程的线程情况
top -H -p 2456

#查看进程启动时间
ps -eo pid,lstart,etime | grep 35507

#查看进程所在目录
ll /proc/35507
ls -al /proc/35507

# 排序
sort

# 去重
uniq

# 查看网络状态
netstat -a

# 查看进程
ps aux

# 机器会在十分钟后关机！幵且会显示在目前登入者的屏幕
shutdown -h 10 'I will shutdown after 10 mins'
# 再过三十分钟系统会重新启劢，会显示后面的讯息给所有在线的使用者
shutdown -r +30 'The system will reboot'
# 仅发出警告信件的参数！系统不会关机啦！
shutdown -k now 'This system will reboot'
# 关机
shutdown -h now
poweroff -f
init 0
# 重启
init 6

# 压缩
tar -czf -f filename.tar.gz
# 解压
#　　1、*.tar 用 tar –xvf 解压
#　　2、*.gz 用 gzip -d或者gunzip 解压
#　　3、*.tar.gz和*.tgz 用 tar –xzf 解压
#　　4、*.bz2 用 bzip2 -d或者用bunzip2 解压
#　　5、*.tar.bz2用tar –xjf 解压
#　　6、*.Z 用 uncompress 解压
#　　7、*.tar.Z 用tar –xZf 解压
#　　8、*.rar 用 unrar e解压
#　　9、*.zip 用 unzip 解压
tar -xzf -f filename.tar.gz

# 0 标准输入 < <<
# 1 标准输出 >(等价于1>) >>
# 2 标准错误输出 2> 2>>
ll 2>&1 info.log

# 1. 若 cmd1 执行完毕且正确执行($?=0)，则开始执行cmd2。
# 2. 若 cmd1 执行完毕且为错误 ($?≠0)，则 cmd2 不会执行。
cmd1 && cmd2

# 1. 若 cmd1 执行完毕且正确执行($?=0)，则 cmd2 不会执行。
# 2. 若 cmd1 执行完毕且为错误 ($?≠0)，则开始执行 cmd2。
cmd1 || cmd2

# 找出syslog的pid
ps aux | grep 'syslog' | grep -v 'grep' | awk '{print $2}'
# 关掉syslog
ps -ef | grep 'syslog' | grep -v grep | awk '{print $2}' | xargs kill -9
# 重新启动syslog
kill -SIGHUP $(ps aux | grep 'syslog' | grep -v 'grep' | awk '{print $2}')
# 确认重新启动情况
tail -5 /var/log/messages
# 作为后台运行
nohup /usr/lib/jvm/java-7-oracle/bin/java -Xms512m -Xmx2048m -jar /spring-cloud/b2c-portal-2.2.3-SNAPSHOT.jar >/dev/null 2>&1 &

# 查看内存
free -m

#清空目录 位于待清空的目录
rm -rf *
#使用绝对路径
rm -rf /home/appadm/logs/*

#查看linux系统信息
#2.6.32-431.el6.x86_64
uname -r

#预设权限
umask

#取文件10-20行
head -n 20 /etc/man.config | tail -n 10

#特殊权限suid
#  SUID 权限仅对二迚制程序(binary program)有效；
#  执行者对亍该程序需要具有 x 癿可执行权限；
#  本权限仅在执行该程序癿过程中有效 (run-time)；
#  执行者将具有该程序拥有者 (owner) 癿权限
#特殊权限sgid
#  SGID 对二迚制程序有用；
#  程序执行者对亍该程序杢说，需具备 x 癿权限；
#  执行者在执行癿过程中将会获得该程序群组癿支持！
#Sticky Bit, sbit 目前叧针对目弽有效
#  弼用户对亍此目弽具有 w, x 权限，亦卲具有写入癿权限时；
#  弼用户在该目弽下建立档案戒目弽时，仅有自己不 root 才有权力删除该档案

#查看文件类型
file

#内容会被先执行
system=$(uname)

# shellcheck disable=SC2006
system=$(uname)

#查看环境变量
env

#480 /etc/passwd 账号说明
#root:x:0:0:root:/root:/bin/bash
#账号名称:密码:uid(0表示root):gid:账号说明:家目录:使用的shell
#481 /etc/shadow 密码说明
#root:$6$3pazmJoW$uTOV8b83QyE0uTIzVugb0QC1kRYiNInwNjQus3U2qEBHKZy750LOsCkKrwNrfMs0fItUVM7ezAee.3XSsRfM01:17879:0:99999:7:::
#账号名称:密码:最近改动密码的日期:密码不可变更的天数限制(0不限制):密码需要变更的天数限制:密码需要变更的前的警告天数:密码过期后的账号宽限日期:账号失效日期:保留
# /etc/group 群组说明
#root:x:0:
#组名:群组密码:gid:此群组支持的账号名称

#查询进程所占端口
sudo netstat -ltp | grep 21324

#查询端口被哪个进程所占
sudo netstat -ltp | grep 20884
lsof -i:20884