package org.javamaster.b2c.cloud.test.gradle.kotlin.model

class Promise<T, U>(private var t: T? = null, private var u: U? = null) {

    fun thenCompose(callback: (t: T) -> U): Promise<T, U> {
        return Promise(t, callback(t!!))
    }

    fun thenAccept(callback: (u: U) -> Unit) {
        callback(u!!)
    }

}
