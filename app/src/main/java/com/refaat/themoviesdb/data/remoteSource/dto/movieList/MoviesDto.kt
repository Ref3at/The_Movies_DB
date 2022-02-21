package com.refaat.themoviesdb.data.remoteSource.dto.movieList

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class MoviesDto(
    @SerializedName("dates")
    @Expose
    var dates: Dates? = null,
    @SerializedName("page")
    @Expose
    var page: Int? = null,
    @SerializedName("results")
    @Expose
    var movieDtos: List<MovieDto>,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
)
