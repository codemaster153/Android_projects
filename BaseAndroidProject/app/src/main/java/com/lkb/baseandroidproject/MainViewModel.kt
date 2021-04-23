package com.lkb.baseandroidproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel", "viewModel cleared called $this")
    }
}