package com.refaat.themoviesdb.data.pagingDataSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.refaat.themoviesdb.common.STARTING_PAGE_INDEX
import com.refaat.themoviesdb.data.remoteSource.TheMovieDatabaseAPI
import com.refaat.themoviesdb.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class PopularMoviesPagingSource(private val service: TheMovieDatabaseAPI) :
    PagingSource<Int, Movie>() {
    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getPopularMovies(position)
            val moviesDto = response.movieDtos
            val movies = moviesDto.map {
                it.toMovie()
            }
            val nextKey = if (movies.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            val error = IOException("Please check the internet connection")
            return LoadResult.Error(error)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}