package com.lkb.baseandroidproject

import android.util.Log
import javax.inject.Inject

class UserRepository @Inject constructor(val loggerService: LoggerService) {
    fun saveUserData(userName: String, password: String) {
        loggerService.logMessage("$userName and $password")
    }
}