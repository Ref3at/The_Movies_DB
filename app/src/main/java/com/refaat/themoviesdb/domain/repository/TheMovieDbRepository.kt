package com.refaat.themoviesdb.domain.repository

import com.refaat.themoviesdb.domain.model.Genre
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.model.MovieDetail

interface TheMovieDbRepository {

    suspend fun getGenreNameById(genreId:Int): String

    suspend fun getMoviesNowPlaying(): List<Movie>
    suspend fun getMoviesPopular(): List<Movie>
    suspend fun getMoviesTopRated(): List<Movie>
    suspend fun getMoviesUpcoming(): List<Movie>

    suspend fun getMoviesForSearchQuery(searchQuery:String): List<Movie>

    suspend fun getMovieDetail(movieId:Int): MovieDetail








}