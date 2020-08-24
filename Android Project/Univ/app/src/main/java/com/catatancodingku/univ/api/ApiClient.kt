package com.catatancodingku.univ.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.53/univ/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : StudentsService = retrofit.create(StudentsService::class.java)
}