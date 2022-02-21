package com.refaat.themoviesdb.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieDetail(
    @PrimaryKey val id: Int?,
    val title: String?,
    val ratingCount: String?,
    val releaseDate: String?,
    val overview: String?,
    val genresString: String?,
    val posterImage: String?,
    val backdropImage: String?,
    var isFavorites: Boolean = false
)