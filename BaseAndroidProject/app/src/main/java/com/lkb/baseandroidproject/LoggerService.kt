package com.lkb.baseandroidproject

import android.util.Log
import javax.inject.Inject

const val TAG = "USER"

class LoggerService @Inject constructor() {
    fun logMessage(msg: String) {
        Log.d(TAG, msg)
    }

}