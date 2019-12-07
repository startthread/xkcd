package com.prakashj.xkcd.infra.di

import com.prakashj.xkcd.BuildConfig
import com.prakashj.xkcd.infra.DispatcherProvider
import com.prakashj.xkcd.infra.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Injections {
    companion object {

        fun provideDispatcherProvider(): DispatcherProvider {
            return DispatcherProvider()
        }

        fun provideApiService(): ApiService {

            val retrofit = Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            return retrofit.create(ApiService::class.java)
        }

        private fun getOkHttpClient(): OkHttpClient {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .build()

            return okHttpClient
        }

        private fun getLoggingInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor()

            loggingInterceptor.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }

            return loggingInterceptor;
        }
    }
}
