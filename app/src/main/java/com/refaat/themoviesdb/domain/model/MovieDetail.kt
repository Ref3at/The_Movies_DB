package com.refaat.themoviesdb.domain.model

class MovieDetail(
    val id: Int?,
    val title: String?,
    val ratingCount: String?,
    val releaseDate: String?,
    val overview: String?,
    val genresString: String?,
    val posterImage: String?,
    val backdropImage: String?,
    val isFavorites: Boolean = false
)