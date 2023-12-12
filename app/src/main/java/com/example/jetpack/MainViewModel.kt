package com.example.jetpack

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _counter = MutableLiveData<Int>()

    val counter: LiveData<Int>
        get() = _counter

    init {
        _counter.value = 0
    }

    fun plus() {
        _counter.value = _counter.value?.plus(1)
    }

    fun plusLater() {
        viewModelScope.launch {
            delay(3000)
            var i = 6
            while (i > 0) {
                _counter.value = _counter.value?.plus(2)
                delay(2000)
                i--
            }
        }
    }

    fun clear() {
        _counter.value = 0
    }

    //工厂构造官方建议方式
//    companion object {
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//                return MainViewModel(extras[])
//            }
//        }
//    }

    //工厂构造方法2
//    inner class MyFactory(private val count: Int) : ViewModelProvider.Factory {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return MainViewModel(count) as T
//        }
//    }
}