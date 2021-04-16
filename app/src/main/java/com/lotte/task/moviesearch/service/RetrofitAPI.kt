package com.lotte.task.moviesearch.service

import com.lotte.task.moviesearch.model.DataModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    // query or keyword
    @GET("search_json2.jsp")
    fun getSearchData(
        @Query("query") keyword: String,
        @Query("detail") detail: String = "Y",
        @Query("listCount") listCount: Int = 100,
        @Query("ServiceKey") ServiceKey: String = "KV1W098T51W72U5AL8S8",
        @Query("collection") collection: String = "kmdb_new2"
    ): Single<DataModel>
}