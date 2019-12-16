package com.prakashj.xkcd.infra.network

data class Comic(
    val num: Int = 0,
    val title: String = "",
    val safeTitle: String = "",
    val alt: String = "",
    val transcript: String = "",
    val img: String = "",
    val day: String = "",
    val month: String = "",
    val year: String = "",
    val link: String = "",
    val news: String = ""
)
