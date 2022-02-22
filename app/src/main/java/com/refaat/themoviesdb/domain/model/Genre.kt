package com.refaat.themoviesdb.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

@Entity
class Genre(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null
)