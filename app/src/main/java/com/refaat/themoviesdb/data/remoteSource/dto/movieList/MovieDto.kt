package com.refaat.themoviesdb.data.remoteSource.dto.movieList

import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.refaat.themoviesdb.common.BASE_IMAGES_URL
import com.refaat.themoviesdb.common.sdf
import com.refaat.themoviesdb.common.sdfDto
import com.refaat.themoviesdb.domain.model.Movie

data class MovieDto(
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,
    @SerializedName("overview")
    @Expose
    var overview: String? = null,
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("video")
    @Expose
    var video: Boolean? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null,
) {
    fun toMovie(): Movie {
        releaseDate = if (releaseDate.isNullOrBlank()) {
            "Pub: N/A"
        } else {
            "Pub: ${sdf.format(sdfDto.parse(releaseDate))}"
        }
        return Movie(
            id = id,
            title = title,
            releaseDate = releaseDate,
            ratingCount = "$voteAverage ($voteCount)",
            posterImage = "$BASE_IMAGES_URL$posterPath",
            backdropImage = "$BASE_IMAGES_URL$backdropPath",
            genreIds = genreIds,
            genreString = genreIds.joinToString()
        )
    }

}