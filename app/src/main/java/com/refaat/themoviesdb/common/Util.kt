package com.refaat.themoviesdb.common

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import java.text.SimpleDateFormat

const val SPAN_COUNT = 2
const val PARAM_PAGE = "page"
const val PARAM_MOVIE_ID = "movie-id"
const val PARAM_SEARCH_QUERY = "query"
const val STARTING_PAGE_INDEX = 1
const val BASE_IMAGES_URL = "https://image.tmdb.org/t/p/original"
val sdfDto = SimpleDateFormat("yyyy-MM-dd")
val sdf = SimpleDateFormat("MMM yyyy")


fun getTheImageURL(countryId: String?): String {
    return "https://flagcdn.com/w160/${countryId?.lowercase()}.png"
}

fun getTheRecyclerViewLayoutManager(context: Context?): GridLayoutManager {
    return GridLayoutManager(context, SPAN_COUNT)
}

fun getTheRecyclerViewItemDecoration(
    spacing: Int,
    includeEdge: Boolean
): GridSpacingItemDecoration {
   return GridSpacingItemDecoration(
        SPAN_COUNT,
        spacing,
        includeEdge
    )
}
