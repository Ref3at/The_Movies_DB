package com.refaat.themoviesdb.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.refaat.themoviesdb.common.Resource
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface TheMovieDbRepository {

    suspend fun getGenreNameById(genreId: Int): String

    fun getMoviesNowPlaying(): Flow<PagingData<Movie>>
    fun getMoviesPopular(): Flow<PagingData<Movie>>
    fun getMoviesTopRated(): Flow<PagingData<Movie>>
    fun getMoviesUpcoming(): Flow<PagingData<Movie>>
    fun getMoviesOfSearchQuery(searchQuery: String): LiveData<PagingData<Movie>>

    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>

    suspend fun addMovieToFavorites(movieDetail: MovieDetail)
    suspend fun getMovieFromFavorites(movieId: Int)
    suspend fun deleteMovieFromFavorites(movieDetail: MovieDetail)

    suspend fun getAllFavoritesMovies(): Flow<List<Movie>>
    suspend fun deleteAllFavoritesMovies()


}