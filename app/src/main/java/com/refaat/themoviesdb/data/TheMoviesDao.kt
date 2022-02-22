package com.refaat.themoviesdb.data

import androidx.room.*
import com.refaat.themoviesdb.domain.model.Genre
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

    @Query("DELETE FROM MovieDetail")
    suspend fun deleteAllMoviesDetail()



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServerGenres(genreList: List<Genre>)

    @Query("SELECT * from Genre WHERE id=:genreId")
    suspend fun getGenreNameById(genreId: Int): Genre?

}