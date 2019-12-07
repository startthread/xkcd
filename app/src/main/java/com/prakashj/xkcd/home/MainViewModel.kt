package com.prakashj.xkcd.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prakashj.xkcd.infra.DispatcherProvider
import com.prakashj.xkcd.infra.network.ApiService
import com.prakashj.xkcd.infra.network.Comic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

private const val TAG = "MainViewModel"

class MainViewModel(
    private val apiService: ApiService,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    val comicLiveData: MutableLiveData<Comic> = MutableLiveData()

    fun getCurrentComic() {
        viewModelScope.launch(dispatcherProvider.IO) {
            val comicResponse: Response<Comic> = apiService.getCurrentComic()
            //Log.d(TAG, "getCurrentComic thread: " + Thread.currentThread())

            if (comicResponse.isSuccessful) {
                comicResponse.body()?.apply {
                    comicLiveData.postValue(comicResponse.body())
                }
            }
        }
    }

    fun onNextComicClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentComic = comicLiveData.value

            currentComic?.apply {
                val nextComicNumber = currentComic.num + 1
                val comicResponse: Response<Comic> = apiService.getComic(nextComicNumber)

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
                val prevComicNumber = currentComic.num - 1
                val comicResponse: Response<Comic> = apiService.getComic(prevComicNumber)

                if (comicResponse.isSuccessful) {
                    comicResponse.body()?.apply {
                        comicLiveData.postValue(comicResponse.body())
                    }
                }
            }
        }
    }
}
