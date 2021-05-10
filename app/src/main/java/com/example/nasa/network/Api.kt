package com.example.nasa.network

import com.example.nasa.modal.Planetary
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("planetary/apod?api_key=DEMO_KEY&count=10")
    fun getAllPlanetary(): Call<List<Planetary>>
}