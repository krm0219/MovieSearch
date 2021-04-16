package com.lotte.task.moviesearch.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {

    private const val URL = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getClient(url: String): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

//    private fun getClient(url: String): Retrofit = Retrofit.Builder()
//        .baseUrl(url)
//        .client(OkHttpClient())
//        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

    val client: RetrofitAPI = getClient(URL).create(RetrofitAPI::class.java)
}