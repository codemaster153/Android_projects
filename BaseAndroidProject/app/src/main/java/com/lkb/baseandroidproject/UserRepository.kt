package com.lkb.baseandroidproject

import android.util.Log


interface UserRepository {
    fun saveUserData(userName: String, password: String)
}

class SQLRepository() : UserRepository {
    override fun saveUserData(userName: String, password: String) {
        Log.d("REPO", "stored in sql DB")
    }
}

class FirebaseRepository() : UserRepository {
    override fun saveUserData(userName: String, password: String) {
        Log.d("REPO", "stored in Firebase")
    }
}