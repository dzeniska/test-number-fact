package com.dzenis_ska.interesting_facts_about_numbers.model.remote

import com.dzenis_ska.interesting_facts_about_numbers.model.Const
import com.dzenis_ska.interesting_facts_about_numbers.model.remote.entities.NumberFactResponseBody
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("{number}?json")
    suspend fun getFactOfNumber(
        @Path("number") number: String
    ): NumberFactResponseBody
    @GET("random?json")
    suspend fun getFactOfRandomNumber(): NumberFactResponseBody

    companion object Factory {
        fun create(): NumbersApi {
            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val moshi = Moshi.Builder().build()
            val moshiConverterFactory =
                MoshiConverterFactory.create(moshi)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(moshiConverterFactory)
                .client(client)
                .build()
            return retrofit.create(NumbersApi::class.java)
        }
    }

}