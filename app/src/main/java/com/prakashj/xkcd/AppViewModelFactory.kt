package com.prakashj.xkcd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel() as T
        }

        throw IllegalStateException("unable to create view model object for: ${modelClass.name}")
    }
}
