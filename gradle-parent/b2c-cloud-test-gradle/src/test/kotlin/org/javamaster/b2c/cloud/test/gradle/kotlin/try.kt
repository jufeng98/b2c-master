package org.javamaster.b2c.cloud.test.gradle.kotlin

import java.nio.file.Files
import java.nio.file.Paths

/**
 *
 * @author yudong
 * @date 2020/6/23
 */
fun stream() {
    // Java 7 的 try with resources
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
}