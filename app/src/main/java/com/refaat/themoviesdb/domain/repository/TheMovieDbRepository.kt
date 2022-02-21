package com.refaat.themoviesdb.domain.repository

import androidx.paging.PagingData
import com.refaat.themoviesdb.domain.model.Genre
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface TheMovieDbRepository {

    //    suspend fun getGenreNameById(genreId:Int): String
//
    fun getMoviesNowPlaying(): Flow<PagingData<Movie>>
    fun getMoviesPopular(): Flow<PagingData<Movie>>
    fun getMoviesTopRated(): Flow<PagingData<Movie>>

//    suspend fun getMoviesUpcoming(): List<Movie>
//
//    suspend fun getMoviesForSearchQuery(searchQuery:String): List<Movie>
//
//    suspend fun getMovieDetail(movieId:Int): MovieDetail


}