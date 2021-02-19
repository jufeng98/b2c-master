rem 找出占用9080端口的进程PID
netstat -aon|findstr "9080"

rem 强制关闭PID为10284的进程
taskkill /F /PID 10284

rem 删除当前目录下所有目录与文件
deltree *.*
rem 删除当前目录所有文件
del *.*

rem %0表示文件名本身，字符串用%1到%9顺序表示
if "参数" == "字符串 " 待执行的命令
if exist 文件名 待执行的命令
if errorlevel 2 待执行的命令

for %%c in (*.bat *.txt) do type %%c