package com.refaat.themoviesdb.data.remoteSource.dto

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.refaat.themoviesdb.domain.model.Genre

class GenreDto(
    @SerializedName("genres")
    @Expose
    var genres: List<Genre>
)