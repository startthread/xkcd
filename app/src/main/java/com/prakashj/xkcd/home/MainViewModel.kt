package com.prakashj.xkcd.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prakashj.xkcd.infra.network.ApiService
import kotlinx.coroutines.launch

class MainViewModel(private val apiService: ApiService) : ViewModel() {

    val TAG = "MainViewModel"

    init {
        getCurrentComic()
    }

    private fun getCurrentComic() {
        viewModelScope.launch {
            val comicResponse = apiService.getCurrentComic()

            Log.d(TAG, comicResponse.toString())

            Log.d(TAG, comicResponse.body().toString())
        }
    }
}