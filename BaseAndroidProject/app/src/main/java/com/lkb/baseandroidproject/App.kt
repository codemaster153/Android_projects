package com.lkb.baseandroidproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        userRepository.saveUserData("lalit", "1234")
    }
}