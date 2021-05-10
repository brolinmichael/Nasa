package com.example.nasa.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Url.URL_DATA)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var api: Api = retrofit.create(Api::class.java)
}