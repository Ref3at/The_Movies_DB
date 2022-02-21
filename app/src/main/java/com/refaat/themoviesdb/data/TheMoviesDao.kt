package com.refaat.themoviesdb.data

import androidx.room.*
import com.refaat.themoviesdb.domain.model.MovieDetail

@Dao
interface TheMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail: MovieDetail)

    @Query("SELECT * from MovieDetail WHERE id=:movieId")
    suspend fun getMovieDetail(movieId: Int): MovieDetail

    @Query("SELECT * from MovieDetail")
    suspend fun getAllMovies(): List<MovieDetail>

    @Delete
    suspend fun deleteMovieDetail(movieDetail: MovieDetail)

}