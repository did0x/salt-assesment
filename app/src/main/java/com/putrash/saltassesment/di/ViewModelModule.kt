package com.putrash.saltassesment.di

import com.putrash.saltassesment.arch.vm.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(androidApplication(), get()) }
}