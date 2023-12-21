package com.example.jetpack.my.kotlin

import kotlin.reflect.KProperty

interface Base {
    fun doSomething()
}

//委托类
class BaseImpl : Base {
    override fun doSomething() {
        println("BaseImpl do something...")
    }
}

//通过by关键字完成委托，Derived相当于代理类
class Derived(base: Base) : Base by base

fun main(args: Array<String>) {
    val base = BaseImpl()
    val derived = Derived(base)
    derived.doSomething()

    val proxy = PropertyProxy()
    println(proxy.str)
    println("------")
    println(proxy.str)
}

class MySet<T>(private val helperSet: HashSet<T>) : Set<T> by helperSet {

    fun helloWorld() = println("Hello Kotlin World")

    override fun isEmpty(): Boolean {
        return false
    }
}

class PropertyProxy {
    var p by Delegate()

    val str: String by lazy {
        println("enter lazy()")
        "Jay"
    }
}

class Delegate {

    var propertyValue: Any? = null

    operator fun getValue(proxy: PropertyProxy, property: KProperty<*>): Any? {
        return propertyValue
    }

    operator fun setValue(proxy: PropertyProxy, property: KProperty<*>, any: Any?) {
        propertyValue = any
    }

}