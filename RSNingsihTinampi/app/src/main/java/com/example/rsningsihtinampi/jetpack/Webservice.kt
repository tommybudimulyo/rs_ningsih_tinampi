package com.example.rsningsihtinampi.jetpack

import com.example.rsningsihtinampi.model.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface Webservice {
    @POST("login")
    fun login(@Body body: RequestBody): Call<LoginResponse>

    companion object Factory {
        fun create(baseUrl: String): Webservice {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(Webservice::class.java)
        }
    }
}