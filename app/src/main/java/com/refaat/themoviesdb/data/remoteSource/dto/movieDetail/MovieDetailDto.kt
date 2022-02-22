package com.refaat.themoviesdb.data.remoteSource.dto.movieDetail

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.refaat.themoviesdb.common.BASE_IMAGES_URL
import com.refaat.themoviesdb.common.sdf
import com.refaat.themoviesdb.common.sdfDto
import com.refaat.themoviesdb.domain.model.Genre
import com.refaat.themoviesdb.domain.model.MovieDetail

class MovieDetailDto(
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: BelongsToCollection? = null,

    @SerializedName("budget")
    @Expose
    var budget: Int? = null,

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null,
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null,

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

    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>? = null,

    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>? = null,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,

    @SerializedName("revenue")
    @Expose
    var revenue: Int? = null,

    @SerializedName("runtime")
    @Expose
    var runtime: Int? = null,

    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("tagline")
    @Expose
    var tagline: String? = null,

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
    var voteCount: Int? = null
) {
    fun toMovieDetail(): MovieDetail {
        return MovieDetail(
            id = this.id,
            title = this.title,
            ratingCount = "$voteAverage ($voteCount)",
            releaseDate = "Pub: ${sdf.format(sdfDto.parse(this.releaseDate))}",
            overview = this.overview,
            genresString = genres?.joinToString { it.name.toString() },
            posterImage = "$BASE_IMAGES_URL$posterPath",
            backdropImage = "$BASE_IMAGES_URL$backdropPath"
        )
    }
}

class SpokenLanguage(
    @SerializedName("english_name")
    @Expose
    var englishName: String? = null,
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null
)

class ProductionCountry(
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null
)

class ProductionCompany(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("logo_path")
    @Expose
    var logoPath: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null
)

class BelongsToCollection(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
)