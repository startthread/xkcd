package com.prakashj.xkcd.infra.di

import com.prakashj.xkcd.infra.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Injections {
    companion object {

        fun provideApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
