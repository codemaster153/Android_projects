package com.lkb.baseandroidproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val networkService = NetworkService.getInstance()
    val countDownFlow = flow<Int> {
        val initialValue = 10
        var currentValue = initialValue
        emit(currentValue)
        call1()
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    suspend fun call1() {
        viewModelScope.launch {
            val res1 = async {
                Log.d(">>>", "call 1")
                networkService.getData1()
            }
            val res2 = async {
                Log.d(">>>", "call 2")
                networkService.getData2()
            }
            val data = res1.await()
            Log.d(">>>", "${res2.isActive}")
            data.collectLatest {
                if (it.isNotEmpty()) {
                    Log.d(">>>", it)
                    //res2.cancelAndJoin()
                }
            }
            val data1 = res2.await()
            data1.collectLatest { Log.d(">>>", it) }


        }
    }
}