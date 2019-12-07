package com.prakashj.xkcd.infra.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prakashj.xkcd.home.MainViewModel

class AppViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(
                Injections.provideApiService(),
                Injections.provideDispatcherProvider()
            ) as T
        }

        throw IllegalStateException("unable to create view model object for: ${modelClass.name}")
    }
}
