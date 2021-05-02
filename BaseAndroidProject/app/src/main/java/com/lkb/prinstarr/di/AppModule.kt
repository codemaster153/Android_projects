package com.lkb.prinstarr.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.lkb.prinstarr.SHARED_PREF_NAME
import com.lkb.prinstarr.view.login.LoginViewModel
import com.lkb.prinstarr.view.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        provideDataBase()
    }
    single {
        provideSharedPreference(androidApplication())
    }
    viewModel {
        MainViewModel(get(), get())
    }

    viewModel {
        LoginViewModel(get(), get())
    }
    single { createConfigUseCase(get()) }
    single { createConfigRepository(get()) }

}

fun provideDataBase(): DatabaseReference {
    return Firebase.database.reference
}

fun provideSharedPreference(app: Application): SharedPreferences {
    return app.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
}

