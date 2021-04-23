package com.lkb.baseandroidproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {


    var userName = LiveData

    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel", "viewModel cleared called $this")
    }
}