#jps 虚拟机进程状况工具
jps -q
#只输出LVMID，省略主类名称
jps -m
#输出启动时传递给main方法的参数
jps -l
#输出主类的全名，如果是jar包，输出jar包路径
jps -v
#输出启动时JVM参数

#jstat 虚拟机统计信息监视工具
#监视类加载、卸载、总空间及耗费的时间
jstat -class
#Loaded  Bytes  Unloaded  Bytes     Time
# 17026 31327.5      145   213.0       7.15
#监视堆状况，包括伊甸区(E表示)、幸存区(S表示)、老年代(O表示)、永久代(P表示)、M(metaspace)、CC(当前压缩类空间)等容量，已用空间，GC时间等
# C(capacity) U(used)
jstat -gc
# S0C    S1C      S0U    S1U      EC       EU        OC         OU       MC     MU      CCSC   CCSU       YGC    YGCT   FGC     FGCT     GCT
#68224.0 68224.0 2577.6  0.0   273152.0 93449.0   114688.0   79172.9   96512.0 94223.2 11776.0 11359.1    562    6.321   8      0.323    6.644
#各个区的已用占比
jstat -gcutil
#  S0     S1     E      O      M     CCS      YGC     YGCT    FGC    FGCT     GCT
#  3.78   0.00 100.00  69.03  97.63  96.46    562    6.321     8    0.323    6.644
#NG新生代 OG老年代 M元空间
jstat -gccapacity
# NGCMN    NGCMX     NGC     S0C      S1C      EC      OGCMN      OGCMX       OGC         OC       MCMN     MCMX       MC      CCSMN    CCSMX     CCSC      YGC    FGC
#409600.0 409600.0 409600.0 68224.0 68224.0 273152.0   114688.0   114688.0   114688.0   114688.0    0.0   1134592.0  96512.0    0.0  1048576.0  11776.0    567     8
#TT:持有次数限制 MTT：最大持有次数限制 DSS：期望的幸存区大小
jstat -gcnew
# S0C    S1C    S0U    S1U     TT MTT   DSS      EC       EU       YGC     YGCT
#68224.0 68224.0    0.0 4507.8 15  15 34112.0 273152.0 118495.6    571    6.397

#实时查看和调整虚拟机各项参数以及系统属性和系统环境
#1.6以上可用java -XX:+PrintFlagsFinal查看所有参数
#参数使用方式有如下三种:
#-XX:+<option> 开启option参数
#-XX:-<option> 关闭option参数
#-XX:<option>=<value> 将option参数值设为value
jinfo -flags PID
java -XX:+PrintFlagsFinal

#jmap Java内存映像工具，用于生成堆转储快照，一般称为dump文件
#生成dump文件,live表示是否只dump出存活对象
jmap -dump:live,format=b,file=filename
#显示F-Queue等待执行的finalize方法对象，只在linux环境有效
jmap -finalizeinfo
#显示java堆详细信息，如使用哪种收集器，参数配置， 分代状况等，只在linux环境有效
jmap -heap
#显示堆中对象统计信息，包括类、实例数量和合计容量
#以ClassLoader为口径统计永久代内存状况，只在linux环境有效
jmap -permstat
#-F 当使用-dump虚拟机没有响应时，强制生成dump快照，只在linux环境有效

#jhat dump快照分析工具
#分析jmap生成的dump文件，然后启动一个微型的http服务器，浏览器访问http://localhost:7000，分析内存泄露主要用到Heap Histogram
jhat heap.bin

#jstack 堆栈跟踪工具，生成所有线程的堆栈快照，主要用于定位线程出现长时间停顿的原因，如死锁、死循环、请求外部资源导致的长
#时间等待等。
#当正常输出的请求不被响应时，强制输出线程堆栈
jstack -F
#除了堆栈外，显示关于锁的附加信息
jstack -l
#如果调用到本地方法，可以显示C/C++的堆栈
jstack -m