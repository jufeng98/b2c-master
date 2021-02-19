package com.javamaster.b2c.cloud.test.kotlin

import org.springframework.util.ReflectionUtils
import org.springframework.util.StreamUtils
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.lang.management.ManagementFactory
import java.nio.charset.Charset

/**
 *
 * @author yudong
 * @date 2020/6/17
 */
class AppUtils {

    companion object {

        fun touchThreadLocalContent(): Map<String, Any> {
            val field = Thread::class.java.getDeclaredField("threadLocals")
            field.isAccessible = true
            val map = field.get(Thread.currentThread())
            val field1 = map.javaClass.getDeclaredField("table")
            field1.isAccessible = true
            val contentMap = mutableMapOf<String, Any>()
            val entrys = field1.get(map)
            val length = java.lang.reflect.Array.getLength(entrys)
            for (i in 0 until length) {
                val entry = java.lang.reflect.Array.get(entrys, i)
                if (entry != null) {
                    val field2 = entry.javaClass.getDeclaredField("value")
                    field2.isAccessible = true
                    val value = field2.get(entry)

                    val field3 = ReflectionUtils.findField(entry.javaClass, "referent")!!
                    field3.isAccessible = true
                    val key = field3.get(entry)
                    if (value != null) {
                        contentMap["""${key.javaClass.name} $key"""] = value
                    }
                }
            }
            return contentMap
        }

        fun currentStackTrace(): List<String> {
            val trace = Thread.currentThread().stackTrace
            val list = mutableListOf<String>()
            trace.forEach {
                list.add(it.toString())
            }
            return list
        }

        fun touchServletContextAttrs(): MutableMap<String, Any> {
            val map = mutableMapOf<String, Any>()
            val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
            val servletContext = request.servletContext
            for (attributeName in servletContext.attributeNames) {
                map[attributeName] = servletContext.getAttribute(attributeName)
            }
            return map
        }

        @JvmStatic
        fun main(args: Array<String>) {
            printMBeanInfos()
            printRuntime()
            printSystem()
            printWindow()
        }

        fun printMBeanInfos() {
            val mbean = ManagementFactory.getClassLoadingMXBean()
            println("totalLoadedClassCount:" + mbean.totalLoadedClassCount)
            val mbean1 = ManagementFactory.getCompilationMXBean()
            println("totalCompilationTime:" + mbean1.totalCompilationTime)
            val mbeans = ManagementFactory.getGarbageCollectorMXBeans()
            println("collectionTime:" + mbeans[0].collectionTime + "ms")
            val mbean2 = ManagementFactory.getMemoryMXBean()
            println("heapMemoryUsage init:" + mbean2.heapMemoryUsage.init / (1024 * 1024) + "MB" + " used:" + mbean2.heapMemoryUsage.used / (1024 * 1024) + "MB"
                    + " committed:" + mbean2.heapMemoryUsage.committed / (1024 * 1024) + "MB" + " max:" + mbean2.heapMemoryUsage.max / (1024 * 1024) + "MB")
            println("nonHeapMemoryUsage init:" + mbean2.nonHeapMemoryUsage.init / (1024 * 1024) + "MB" + " used:" + mbean2.nonHeapMemoryUsage.used / (1024 * 1024) + "MB"
                    + " committed:" + mbean2.nonHeapMemoryUsage.committed / (1024 * 1024) + "MB" + " max:" + mbean2.nonHeapMemoryUsage.max)
            val mbeans2 = ManagementFactory.getMemoryManagerMXBeans()
            println("memoryPoolNames:" + mbeans2[0].memoryPoolNames)
            val mbeans3 = ManagementFactory.getMemoryPoolMXBeans()
            println("collectionUsage:" + mbeans3[0].collectionUsage)
            val mbean4 = ManagementFactory.getOperatingSystemMXBean()
            println("availableProcessors:" + mbean4.availableProcessors)
            val mbeanServer = ManagementFactory.getPlatformMBeanServer()
            println("mBeanCount:" + mbeanServer.mBeanCount)
            val mbean5 = ManagementFactory.getRuntimeMXBean()
            println("pid:" + mbean5.name.split("@")[0] + " " + mbean5.uptime + "ms")
            val mbean6 = ManagementFactory.getThreadMXBean()
            println("threadCount:" + mbean6.threadCount)
        }

        fun printRuntime() {
            val runtime = Runtime.getRuntime()
            println("free memory:" + runtime.freeMemory() / (1024 * 1024) + "MB")
            println("total memory:" + runtime.totalMemory() / (1024 * 1024) + "MB")
            val cmd = arrayOf("cmd", "/C", "ipconfig")
            val process = runtime.exec(cmd)
            println(StreamUtils.copyToString(process.inputStream, Charset.forName("GBK")))
            println("------------------")
            println(StreamUtils.copyToString(process.errorStream, Charset.forName("GBK")))
            println("------------------")
        }

        fun printSystem() {
            println("lineSeparator:" + System.lineSeparator())
            println("env:" + System.getenv())
            println("properties:" + System.getProperties())
            val console = System.console() ?: return
            val username = console.readLine("User name: ");
            val password = console.readPassword("PassWord: ");
            println("$username ${String(password)}")
        }

        fun printWindow() {
            val toolkit = Toolkit.getDefaultToolkit()

            println("" + toolkit.screenSize.width + " " + toolkit.screenSize.height)

            println(toolkit.colorModel)

            println(toolkit.screenResolution)

            println(toolkit.systemClipboard.getContents(null).getTransferData(DataFlavor.stringFlavor))

        }

    }

}