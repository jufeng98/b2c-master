set  output-directory=..\..\..\target
set  source-directory=..\java
set  java8_home=D:\java\64\jdk1.8.0_131
if exist %output-directory% (echo "directory already exists") else (md %output-directory%\classes\org\javamaster\annotation\processor\annotation)
%java8_home%\bin\javac -version
rem 先编译注解
%java8_home%\bin\javac -classpath %classpath% -d %output-directory%\classes %source-directory%\org\javamaster\annotation\processor\annotation\*
rem 再编译service
%java8_home%\bin\javac -classpath %java8_home%/lib/tools.jar;%java8_home%/jre/lib/rt.jar;%output-directory%\classes -d %output-directory%\classes %source-directory%\org\javamaster\annotation\processor\service\*
rem 再编译注解处理器
%java8_home%\bin\javac -classpath %java8_home%/lib/tools.jar;%java8_home%/jre/lib/rt.jar;%output-directory%\classes -d %output-directory%\classes %source-directory%\org\javamaster\annotation\processor\processor\*