package com.refaat.themoviesdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.refaat.themoviesdb.data.pagingDataSources.NowPlayingMoviesPagingSource
import com.refaat.themoviesdb.data.pagingDataSources.PopularMoviesPagingSource
import com.refaat.themoviesdb.data.pagingDataSources.TopRatedMoviesPagingSource
import com.refaat.themoviesdb.data.pagingDataSources.UpComingMoviesPagingSource
import com.refaat.themoviesdb.data.remoteSource.TheMovieDatabaseAPI
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

class TheMovieDbRepositoryImpl(private val theMovieDatabaseAPI: TheMovieDatabaseAPI) :
    TheMovieDbRepository {

    companion object {
        const val PAGE_SIZE = 20
    }

    override fun getMoviesNowPlaying(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NowPlayingMoviesPagingSource(theMovieDatabaseAPI) }
        ).flow
    }

    override fun getMoviesPopular(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularMoviesPagingSource(theMovieDatabaseAPI) }
        ).flow
    }

    override fun getMoviesTopRated(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopRatedMoviesPagingSource(theMovieDatabaseAPI) }
        ).flow
    }

    override fun getMoviesUpcoming(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UpComingMoviesPagingSource(theMovieDatabaseAPI) }
        ).flow
    }

}