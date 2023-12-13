package com.example.jetpack.ext

import android.app.Activity
import android.content.Intent

//通过泛型实化（由inline和reified关键字限定）简化启动Activity的写法
inline fun <reified T> Activity.jump() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

//可携带参数方法
//其实这里intentScope类型为Intent.()，相当于就是提前把Intent的作用于抛给调用方，
//然后我们再去创建实例，调用intentScope函数。
inline fun <reified T> Activity.jumpWithParams(intentScope: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.intentScope()
    startActivity(intent)
}