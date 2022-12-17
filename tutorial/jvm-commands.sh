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
jstat -class 1620
#监视类加载、卸载、总空间及耗费的时间
#Loaded  Bytes  Unloaded  Bytes     Time
# 17026 31327.5      145   213.0       7.15

#监视堆状况,每隔1000ms刷新一次，共刷新5次
jstat -gc 1620 1000 5
# C(capacity) U(used) 包括伊甸区(E表示)、幸存区(S表示)、老年代(O表示)、永久代(P表示)、M(metaspace)、CC(当前压缩类空间)等容量，已用空间，GC时间(秒)等
# S0C    S1C      S0U    S1U      EC       EU        OC         OU       MC     MU      CCSC   CCSU       YGC    YGCT   FGC     FGCT     GCT
#68224.0 68224.0 2577.6  0.0   273152.0 93449.0   114688.0   79172.9   96512.0 94223.2 11776.0 11359.1    562    6.321   8      0.323    6.644

#各个区的已用占比
jstat -gcutil 1620
#  S0     S1     E      O      M     CCS      YGC     YGCT    FGC    FGCT     GCT
#  3.78   0.00 100.00  69.03  97.63  96.46    562    6.321     8    0.323    6.644

#NG新生代 OG老年代 M元空间
jstat -gccapacity 1620
# NGCMN    NGCMX     NGC     S0C      S1C      EC      OGCMN      OGCMX       OGC         OC       MCMN     MCMX       MC      CCSMN    CCSMX     CCSC      YGC    FGC
#409600.0 409600.0 409600.0 68224.0 68224.0 273152.0   114688.0   114688.0   114688.0   114688.0    0.0   1134592.0  96512.0    0.0  1048576.0  11776.0    567     8

#TT:持有次数限制 MTT：最大持有次数限制 DSS：期望的幸存区大小
jstat -gcnew
# S0C    S1C    S0U    S1U     TT MTT   DSS      EC       EU       YGC     YGCT
#68224.0 68224.0    0.0 4507.8 15  15 34112.0 273152.0 118495.6    571    6.397

#实时查看和调整虚拟机各项参数以及系统属性和系统环境
#参数使用方式有如下三种:
#-XX:+<option> 开启option参数
#-XX:-<option> 关闭option参数
#-XX:<option>=<value> 将option参数值设为value
#查看参数
jinfo -flag PrintGCDetails 1620
#-XX:-PrintGCDetails

#查看参数
jinfo -flag MaxNewSize 1620
#-XX:MaxNewSize=630194176

#查看参数
jinfo -flags 1620
#JVM version is 25.131-b11
#Non-default VM flags: -XX:CICompilerCount=2 -XX:InitialHeapSize=119537664 -XX:MaxHeapSize=1891631104 -XX:MaxNewSize=630194176 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=39845888 -XX:OldSize=79691776 -XX:+UseCompres
#sedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
#Command line:  -javaagent:D:\IntelliJ IDEA 2018.2.3\lib\idea_rt.jar=49325:D:\IntelliJ IDEA 2018.2.3\bin -Dfile.encoding=UTF-8

#1.6以上可用java -XX:+PrintFlagsFinal查看所有参数
java -XX:+PrintFlagsFinal
#     ......
#     bool UsePSAdaptiveSurvivorSizePolicy           = true                                {product}
#     bool UseParNewGC                               = false                               {product}
#     bool UseParallelGC                            := true                                {product}
#     bool UseParallelOldGC                          = true                                {product}
#     bool UsePerfData                               = true                                {product}
#     bool UsePopCountInstruction                    = true                                {product}
#     ......

#jmap Java内存映像工具，用于生成堆转储快照，一般称为dump文件
#生成dump文件,live表示是否只dump出存活对象
#-F 当使用-dump虚拟机没有响应时，强制生成dump快照，只在linux环境有效
jmap -dump:live,format=b,file=java_pid.hprof 8
#Dumping heap to D:\open-source\b2c-master\eureka.hprof ...
#Heap dump file created

#显示F-Queue等待执行的finalize方法对象，只在linux环境有效
jmap  -finalizerinfo 49270
#JVM version is 25.66-b17
#Number of objects pending for finalization: 0

#显示java堆详细信息，如使用哪种收集器，参数配置， 分代状况等，只在linux环境有效
jmap -heap 49270
#using parallel threads in the new generation.
#using thread-local object allocation.
#Concurrent Mark-Sweep GC
#
#Heap Configuration:
#   MinHeapFreeRatio         = 40
#   MaxHeapFreeRatio         = 70
#   MaxHeapSize              = 536870912 (512.0MB)
#   NewSize                  = 178913280 (170.625MB)
#   MaxNewSize               = 178913280 (170.625MB)
#   OldSize                  = 357957632 (341.375MB)
#   NewRatio                 = 2
#   SurvivorRatio            = 8
#   MetaspaceSize            = 268435456 (256.0MB)
#   CompressedClassSpaceSize = 1073741824 (1024.0MB)
#   MaxMetaspaceSize         = 268435456 (256.0MB)
#   G1HeapRegionSize         = 0 (0.0MB)
#
#Heap Usage:
#New Generation (Eden + 1 Survivor Space):
#   capacity = 161021952 (153.5625MB)
#   used     = 12285752 (11.716606140136719MB)
#   free     = 148736200 (141.84589385986328MB)
#   7.62986154831858% used
#Eden Space:
#   capacity = 143130624 (136.5MB)
#   used     = 10443328 (9.95953369140625MB)
#   free     = 132687296 (126.54046630859375MB)
#   7.296361678685898% used
#From Space:
#   capacity = 17891328 (17.0625MB)
#   used     = 1842424 (1.7570724487304688MB)
#   free     = 16048904 (15.305427551269531MB)
#   10.297860505380036% used
#To Space:
#   capacity = 17891328 (17.0625MB)
#   used     = 0 (0.0MB)
#   free     = 17891328 (17.0625MB)
#   0.0% used
#concurrent mark-sweep generation:
#   capacity = 357957632 (341.375MB)
#   used     = 155148904 (147.9615249633789MB)
#   free     = 202808728 (193.4134750366211MB)
#   43.34281214599162% used
#
#53938 interned Strings occupying 5686200 bytes.

#显示堆中对象统计信息，包括类、实例数量和合计容量
jmap -histo 49270
# num     #instances         #bytes  class name
#----------------------------------------------
#   1:        264610       30828176  [C
#   2:          2393       19360752  [J
#   3:         64064       11236320  [Ljava.lang.Object;
#......
#9397:             1             16  tk.mybatis.spring.mapper.SpringBootBindUtil$SpringBoot2Bind
#Total       2505791      156259200

#jhat dump快照分析工具
#分析jmap生成的dump文件，然后启动一个微型的http服务器，浏览器访问http://localhost:7000，分析内存泄露主要用到Heap Histogram
jhat java_pid1620.hprof
#......
#Snapshot resolved.
#Started HTTP server on port 7000
#Server is ready.

#jstack 堆栈跟踪工具，生成所有线程的堆栈快照，主要用于定位线程出现长时间停顿的原因，如死锁、死循环、请求外部资源导致的长
#时间等待等。
#当正常输出的请求不被响应时，强制输出线程堆栈
jstack -F 1620
#除了堆栈外，显示关于锁的附加信息
jstack -l 1620
#如果调用到本地方法，可以显示C/C++的堆栈
jstack -m 1620