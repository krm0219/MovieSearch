package com.lotte.task.moviesearch.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat

@JsonClass(generateAdapter = true)
data class DataModel(
    @Json(name = "KMAQuery") val query: String,
    @Json(name = "TotalCount") val totalCount: Int,
    @Json(name = "Data") val items: List<MoviesModel>
)

@JsonClass(generateAdapter = true)
data class MoviesModel(
    @Json(name = "Count") val count: Int,
    @Json(name = "Result") val movies: List<MovieModel>
)

@JsonClass(generateAdapter = true)
data class MovieModel(
    @Json(name = "movieSeq") val movieSeq: String,
    @Json(name = "title") val title: String,
    @Json(name = "directors") val directors: Directors,
    @Json(name = "repRlsDate") val releaseDate: String,
    @Json(name = "posters") var posterUrl: String,
) {

    fun getDirectorName(): String {

        var name = directors.director[0].directorNm
        name = name.replace("!HS", "")
        name = name.replace("!HE", "")
        return name.trim()
    }

    fun getDate(): String {

        return if (releaseDate.isNotEmpty()) {

            val dateFormat = SimpleDateFormat("yyyyMMdd")
            val date = dateFormat.parse(releaseDate)

            val format = SimpleDateFormat("yyyy-MM-dd")
            format.format(date)
        } else {

            releaseDate
        }
    }

    fun getPoster(): String {

        return if (posterUrl.contains('|')) {

            val posters = posterUrl.split("|")
            posters[0]
        } else {

            posterUrl
        }
    }
}

@JsonClass(generateAdapter = true)
data class Directors(
    @Json(name = "director") val director: List<Director>
)

@JsonClass(generateAdapter = true)
data class Director(
    @Json(name = "directorNm") val directorNm: String
)