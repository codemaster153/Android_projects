package com.lkb.baseandroidproject

import ErrorState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

open class BaseModel : ViewModel() {
    var error: Flow<String> = flowOf()
}