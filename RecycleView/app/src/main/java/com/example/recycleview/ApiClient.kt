package com.example.recycleview

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

class ApiClient {
    companion object {

        private val BASE_URL = "https://demo460.nop-station.com/api/"

        private fun buildClient(): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type","application/json")
                    .addHeader("DeviceId","faisal's android")
                    .addHeader("NST","eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.adqiIzFjqZdpJw5uHOHjE5qw2UvCDH2FwMmwlYvr5ljKyPG65ZQe_4wb8NYEQFXmyZZyVu-77xd5Njn310cjMw")
                    .build()
                chain.proceed(request)
            }.build()
        }

        fun getRetrofit(): Retrofit{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(buildClient())
                .build()
        }
    }
}