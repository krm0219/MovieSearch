package com.lotte.task.moviesearch.service

import com.lotte.task.moviesearch.model.MoviesModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("search_json2.jsp")
    fun getSearchData(
        @Query("keyword") keyword: String,
        @Query("detail") detail: String,
        @Query("listCount") listCount: Int = 100,
        @Query("ServiceKey") ServiceKey: String = "ServiceKey",
        @Query("collection") collection: String = "kmdb_new2"
    ): Single<MoviesModel>
}