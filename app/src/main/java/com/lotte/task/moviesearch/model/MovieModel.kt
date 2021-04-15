package com.lotte.task.moviesearch.model

import com.google.gson.annotations.SerializedName

data class MoviesModel(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val items: ArrayList<MovieModel>
)

data class MovieModel(
    @SerializedName("movieSeq") val movieSeq: String,
    @SerializedName("title") val title: String,
    @SerializedName("directorNm") val directorNm: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("posterUrl") val posterUrl: String,
)