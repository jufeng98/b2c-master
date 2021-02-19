package org.javamaster.b2c.cloud.test.gradle.kotlin

import org.javamaster.b2c.cloud.test.gradle.kotlin.model.Item
import org.javamaster.b2c.cloud.test.gradle.kotlin.model.Promise
import org.javamaster.b2c.cloud.test.gradle.kotlin.model.Token
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

/**
 *
 * @author yudong
 * @date 2020/6/16
 */

fun main(args: Array<String>) {
    val item = Item()
    postItem(item)
    postItemAsync(item)
    postItemPromise(item)
    postItemCoroutine(item)
    thread(start = true) {
        threadLong()
    }
    thread(start = true) {
        coroutineLong()
    }
    value()
//    TimeUnit.SECONDS.sleep(10)
}

// 阻塞式请求
fun postItem(item: Item) {
    val token = preparePost()
    val person = submitPost(token, item)
    processPost(person)
}

fun preparePost(): Token {
    // makes a request and consequently blocks the main thread
    return Token()
}

fun submitPost(token: Token, item: Item): Person {
    return Person(token.id + " " + item.itemName, 23)
}

fun processPost(person: Person) {
    println(person)
}

// 异步式请求,回调函数层层嵌套,难以理解
fun postItemAsync(item: Item) {
    preparePostAsync { token ->
        submitPostAsync(token, item) { person ->
            processPost(person)
        }
    }
}

fun preparePostAsync(callback: (Token) -> Unit) {
    // make request and return immediately
    // arrange callback to be invoked later
    callback(Token())
}

fun submitPostAsync(token: Token, item: Item, callback: (person: Person) -> Unit) {
    callback(Person(token.id + " " + item.itemName, 25))
}

// promise方式
fun postItemPromise(item: Item) {
    preparePostAsync()
            .thenCompose { token ->
                submitPostAsync(token, item)
            }
            .thenAccept { person ->
                processPost(person)
            }

}

fun submitPostAsync(token: Token, item: Item): Person {
    return Person(token.id + " " + item.itemName, 27)
}

fun preparePostAsync(): Promise<Token, Person> {
    // makes request an returns a promise that is completed later
    return Promise(Token())
}


fun postItemCoroutine(item: Item) {
    println("Start")

// Start a coroutine
    GlobalScope.launch {
        delay(1000)
        println("Hello")
    }

    Thread.sleep(2000) // wait for 2 seconds
    println("Stop")
}

suspend fun preparePostCoroutine(): Token {
    // makes a request and suspends the coroutine
    return suspendCoroutine { }
}

fun threadLong() {
    val c = AtomicLong()

    for (i in 1..1_000L)
        thread(start = true) {
            c.addAndGet(i)
        }

    println("thread:" + c.get())
}

fun coroutineLong() {
    val c = AtomicLong()

    for (i in 1..1_000L)
        GlobalScope.launch {
            c.addAndGet(i)
        }

    println("coroutine:" + c.get())
}

fun value() {
    val deferred = (1..1_000_000).map { n ->
        GlobalScope.async {
            n
        }
    }
    runBlocking {
        val sum = deferred.map { it.await().toLong() }.sum()
        println("sum:$sum")
    }
}