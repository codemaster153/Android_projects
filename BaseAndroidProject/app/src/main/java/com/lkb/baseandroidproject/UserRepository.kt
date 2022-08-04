package com.lkb.baseandroidproject

import android.util.Log
import javax.inject.Inject

const val TAG = "USER_REPOSITORY"

class UserRepository @Inject constructor() {
    val userName = "Lalit"
    fun saveUserData(userName: String, password: String) {
        Log.d(TAG, "$userName and $password")
    }
}