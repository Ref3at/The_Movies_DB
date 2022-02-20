package com.refaat.themoviesdb.common

import java.text.SimpleDateFormat

const val PARAM_PAGE = "page"
const val PARAM_MOVIE_ID = "movie-id"
const val PARAM_SEARCH_QUERY = "query"

fun getTheImageURL(countryId: String?): String {

    return "https://flagcdn.com/w160/${countryId?.lowercase()}.png"
}
