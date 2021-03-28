package com.lkb.baseandroidproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var userData: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserData(): LiveData<List<User>> {
        viewModelScope.launch {
            var list = mutableListOf<User>()
            val database = Firebase.database.reference
            database.child(Constants.DB_NAME).get().addOnSuccessListener { it ->
                val iterator = it.children
                iterator.forEach {
                    it.getValue(User::class.java)?.let { it1 -> list.add(it1) }
                }
                userData.postValue(list)
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }
        return userData
    }
}