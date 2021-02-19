package org.javamaster.b2c.cloud.test.gradle.kotlin.model

class Promise<T, U>(private var t: T? = null, private var u: U? = null) {

    fun thenCompose(callback: (t: T) -> U): _root_ide_package_.org.javamaster.b2c.cloud.test.gradle.kotlin.model.Promise<T, U> {
        return _root_ide_package_.org.javamaster.b2c.cloud.test.gradle.kotlin.model.Promise(t, callback(t!!))
    }

    fun thenAccept(callback: (u: U) -> Unit) {
        callback(u!!)
    }

}
