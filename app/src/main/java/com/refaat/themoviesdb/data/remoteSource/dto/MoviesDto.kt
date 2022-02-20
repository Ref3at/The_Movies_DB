package com.refaat.themoviesdb.data.remoteSource.dto

data class MoviesDto(
    val datesDto: DatesDto,
    val page: Int,
    val movieDtos: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)