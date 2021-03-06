package com.refaat.themoviesdb.data.remoteSource

import com.refaat.themoviesdb.common.*
import com.refaat.themoviesdb.data.remoteSource.dto.GenreDto
import com.refaat.themoviesdb.data.remoteSource.dto.movieDetail.MovieDetailDto
import com.refaat.themoviesdb.data.remoteSource.dto.movieList.MoviesDto
import com.refaat.themoviesdb.domain.model.Genre
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDatabaseAPI {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    //Get genre List
    @GET("genre/movie/list")
    suspend fun getGenreList(): GenreDto

    //Get Now Playing Movies
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query(PARAM_PAGE) page: Int): MoviesDto

    //Get Popular Movies
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query(PARAM_PAGE) page: Int): MoviesDto

    //Get Top Rated Movies
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query(PARAM_PAGE) page: Int): MoviesDto

    //Get Upcoming Movies
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query(PARAM_PAGE) page: Int): MoviesDto

    //Get Search Movies
    @GET("search/movie")
    suspend fun getMoviesOfSearchQuery(
        @Query(PARAM_SEARCH_QUERY) searchQuery: String,
        @Query(PARAM_PAGE) page: Int
    ):MoviesDto

    //Get Movie Detail
    @GET("movie/{$PARAM_MOVIE_ID}")
    suspend fun getMovieDetail(@Path(PARAM_MOVIE_ID) movieId: Int): MovieDetailDto
}