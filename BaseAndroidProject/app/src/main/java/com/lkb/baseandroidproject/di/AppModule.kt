package com.lkb.baseandroidproject.di

import com.lkb.baseandroidproject.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{
        MainViewModel()
    }
}