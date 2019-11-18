package com.prakashj.xkcd.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prakashj.xkcd.infra.network.ApiService
import com.prakashj.xkcd.infra.network.Comic
import kotlinx.coroutines.launch
import retrofit2.Response

private const val TAG = "MainViewModel"

class MainViewModel(private val apiService: ApiService) : ViewModel() {

    val comicLiveData: MutableLiveData<Comic> = MutableLiveData()

    fun getCurrentComic() {
        viewModelScope.launch {
            val comicResponse: Response<Comic> = apiService.getCurrentComic()

            Log.d(TAG, comicResponse.toString())
            Log.d(TAG, comicResponse.body().toString())

            if (comicResponse.isSuccessful) {
                comicResponse.body()?.apply {
                    comicLiveData.postValue(comicResponse.body())
                }
            }
        }
    }

    fun onNextComicClick() {
        viewModelScope.launch {
            val currentComic = comicLiveData.value

            currentComic?.apply {
                val comicResponse: Response<Comic> = apiService.getComic(currentComic.num + 1)

                Log.d(TAG, comicResponse.toString())
                Log.d(TAG, comicResponse.body().toString())

                if (comicResponse.isSuccessful) {
                    comicResponse.body()?.apply {
                        comicLiveData.postValue(comicResponse.body())
                    }
                }
            }
        }
    }

    fun onPrevComicClick() {
        viewModelScope.launch {
            val currentComic = comicLiveData.value

            currentComic?.apply {
                val comicResponse: Response<Comic> = apiService.getComic(currentComic.num - 1)

                Log.d(TAG, comicResponse.toString())
                Log.d(TAG, comicResponse.body().toString())

                if (comicResponse.isSuccessful) {
                    comicResponse.body()?.apply {
                        comicLiveData.postValue(comicResponse.body())
                    }
                }
            }
        }
    }
}
