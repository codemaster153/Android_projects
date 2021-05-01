package com.lkb.baseandroidproject.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.lkb.baseandroidproject.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    fun provideDataBase(): DatabaseReference {
        return Firebase.database.reference
    }
    single {
        provideDataBase()
    }
    viewModel {
        MainViewModel(get())
    }
}