package com.refaat.themoviesdb.domain.useCases

import com.refaat.themoviesdb.common.Resource
import com.refaat.themoviesdb.domain.model.MovieDetail
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailUseCase(val repository: TheMovieDbRepository) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> {
        return repository.getMovieDetail(movieId)
    }
}