#!/bin/bash
# 导出变量使得子script也能引用
export APPLICATION_NAME="b2c-cloud-test-user-2.0.0-SNAPSHOT"
echo ${APPLICATION_NAME}
source shell-child.sh

# 读取用户输入
read -p "Please input your first name: " FIRST_NAME
echo "Your first name is:${FIRST_NAME}"

# 根据日期取文件名
# 前两天的日期,格式:20190602
date1=$(date --date='2 days ago' +%Y%m%d)
date2=$(date --date='1 days ago' +%Y%m%d)
date3=$(date +%Y%m%d)
file1=${filename}${date1}
file2=${filename}${date2}
file3=${filename}${date3}
touch "shell/${file1}.log"
touch "shell/${file2}.log"
touch "shell/${file3}.log"

# 加减乘除
x=20
y=3
echo "x+y:$(($x+$y))"
echo "x-y:$(($x-$y))"
echo "x*y:$[$x*$y]"
echo "x/y:$[$x/$y]"
a=3
b=6
echo $((a + b))

#linux私房菜453页
#-e  该『档名』是否存在？(常用)
#-f  该『档名』是否存在且为档案(file)？(常用)
#-d  该『文件名』是否存在且为目录(directory)？(常用)
#检查/dmtsai是否存在
test -e /dmtsai && echo "exist" || echo "Not exist"
#检查redeploy1.sh是否存在且是file
test -f redeploy1.sh && echo "exist" || echo "Not exist"
#检查shell是否存在且是目录
test -d shell && echo "exist" || echo "Not exist"
#453 检查是否存在,得到回传值$?
test -e service-mall-user && echo "exist" || echo "Not exist"
#检查是否存在且为文件,得到回传值$?
test -f service-mall-user && echo "exist" || echo "Not exist"
#检查是否存在且为文件夹,得到回传值$?
test -d service-mall-user && echo "exist" || echo "Not exist"

#-eq  两数值相等 (equal)
#-ne  两数值不等 (not equal)
#-gt  n1 大亍 n2 (greater than)
#-lt  n1 小亍 n2 (less than)
#-ge  n1 大亍等亍 n2 (greater than or equal)
#-le  n1 小亍等亍 n2 (less than or equal)
test 23 -eq 34 && echo "equals" || echo "Not equals"

name=""
#test -z  刞定字符串是否为空字符串？若为空字符串，则为 true
test -z name && echo "0" || echo "1"

##刞定字符串是否不为空字符串？若为空字符串，则为 false
##注： -n 亦可省略
test -n name && echo "0" || echo "1"

##test str1 = str2  刞定 str1 是否等亍 str2 ，若相等，则回传 true
##test str1 != str2  刞定 str1 是否丌等亍 str2 ，若相等，则回传 false
test "hello" == "hello" && echo "equals" || echo "Not equals"

#-a   (and)两状况同时成立！例如 test -r file -a -x file，则 file 同时具有r x 权限时，才回传 true。
#-o   (or)两状况任何一个成立！例如 test -r file -o -x file，则 file 具有 r或 x 权限时，就可回传 true。
# !    反相状态，如 test ! -x file ，当 file 丌具有 x 时，回传 true
test 23 -eq 22 -a "hello" == "hello" && echo "true" || echo "false"

#$x 这个发量是否为空
[ -z "$x" ] && echo "true" || echo "false"
[  "$HOME" != "$MAIL" ] && echo "true" || echo "false"

# 访问命令行参数
echo "$0 $1 $2 $3 $#"
echo "$@"

#455 判断符号 [ ]  判断$HOME 这个发量是否为空
[ -z "$HOME" ]
echo $?

#判读相等性(两边须有空格隔开)
[ "$HOME" = "$MAIL" ]

#459 判断a=y或者a=Y
a=y
if [ "$a" = "y" ] || [ "$a" = "Y" ]; then
  echo "hello"
elif [ "$a" = "n" ] || [ "$a" = "N" ]; then
  echo "world"
else
  echo "!"
fi
#468 循环 计算1+2+...+100
s=0
i=0
while [ "$i" -lt 100 ]; do
  i=$((i + 1))
  s=$((s + i))
done
echo "The result of '1+2+3+...+100' is ==> $s"
#471 for循环
for i in $(seq 1 100); do
  echo "${i}"
done

yn="N"

if [ "$yn" == "N" ] || [ "$yn" == "n" ]; then
    echo "Oh, interrupt a!"
fi

if (${x}>${y}); then
    echo "hello world"
fi

if [ "$yn" == "Y" ] || [ "$yn" == "y" ]; then
    echo "OK, continue"
elif [ "$yn" == "N" ] || [ "$yn" == "n" ]; then
    echo "Oh, interrupt b!"
else
    echo "I don't know what your choice is"
    # exit 退出脚本执行
    exit 0
fi

function printNow(){
    echo "millis:`date +%s` $(date +%s) $0 $1 $2"
}

printNow "yu" "dong"

i=0
while test ${i} -lt 5; do
    echo "index:$i"
    i=$((${i}+1))
done

while [ ${i} -lt 10 ]; do
    echo "index:$i"
    i=$((${i}+1))
done

for animal in dog cat elephant
do
    echo "for animal:${animal}"
done

for index in $(seq 1 6)
do
    echo "for index:${index}"
done

dir="shell"
fileList=$(ls ${dir})  # 列出所有在该目录下的文件名
for filename in ${fileList}
do
    perm=""
    test -r "$dir/$filename" && perm="$perm readable"
    test -w "$dir/$filename" && perm="$perm writable"
    test -x "$dir/$filename" && perm="$perm executable"
    echo "The file $dir/$filename's permission is $perm "
done

s=0
for (( i=1; i<=100; i=i+1 ))
do
    s=$(($s+$i))
done
echo "The result of '1+2+3+...+$nu' is ==> $s"
#  sh -x shell-test.sh 执行过程全部列出来
