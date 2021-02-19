package org.javamaster.b2c.cloud.test.gradle.groovy

import groovy.transform.PackageScope

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 *
 * @author yudong
 * @date 2019/6/10
 */
println 'Hello world'
for (i in 0..2)
    println "current value is ${i}"
0.upto(2) {
    println "current value is ${it}"
}
3.times {
    println "current value is ${it}"
}

println "git help -a".execute().text
println "cmd /C dir".execute().text

println foo("hello")
println foo()

def foo(str) {
    str?.toUpperCase()
}

println getFileStr(Paths.get("/hello.txt"))

def getFileStr(Path path) {
    try {
        Files.readAllLines(path).stream().collect(Collectors.joining(""))
    } catch (e) {
        // do anything what you want to do
        return "unknown"
    }
}

def fruits = ["apple", "banana", "orange"]
println fruits


class Person {
    @PackageScope
    Integer id
    String name
}

def person = new Person()
person.setName("liangyudong")
println person.name
def filePath = "G:\\禁用hyperV.cmd"
new File(filePath).eachLine('UTF-8') {
    println it
}
new File(filePath).withReader('UTF-8') { reader ->
    reader.eachLine {
        println it
    }
}
println new File(filePath).text

Runnable run = { println 'run' }
fruits.each { println it }

println 'c'.getClass() == String
println "c".getClass() == String
println "c${1}".getClass() in GString

def a = 123
def b = 'b'
def c = true
boolean d = false
int e = 123

println 'hello'
def name = '张三'
println "hello $name"
def strippedFirstNewline2 = '''\
line one
    line two
line three
'''
println strippedFirstNewline2

String method1() {
    return 'hello'
}

def method2() {
    return 'hello'
}

def add(a, b) {
    a + b
}

def closure = { String x, int y ->
    println "hey ${x} the value is ${y}"
}

String[] arrStr = ['Ananas', 'Banana', 'Kiwi']
