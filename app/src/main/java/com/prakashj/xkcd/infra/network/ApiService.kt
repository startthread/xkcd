package com.prakashj.xkcd.infra.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val ENDPOINT: String = "https://xkcd.com/"
    }

    @GET("info.0.json")
    suspend fun getCurrentComic(): Response<Comic>

    @GET("{comicNumber}/info.0.json")
    suspend fun getComic(@Path("comicNumber") user: Int): Response<Comic>
}
