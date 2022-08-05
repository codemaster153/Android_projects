package com.lkb.baseandroidproject

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@FirebaseRepo val repo: UserRepository) : ViewModel() {
    fun saveData() {
        repo.saveUserData("", "")
    }
}