package com.lkb.baseandroidproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainViewModel:ViewModel() {

    val countDownFlow = flow {
        val initialValue = 10
        var currentValue = initialValue
        emit(currentValue)
        while (currentValue>0){
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }
}