package com.refaat.themoviesdb.domain.useCases

import androidx.paging.PagingData
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

data class GetPopularUseCase(private val repository: TheMovieDbRepository) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMoviesPopular()
    }

}
