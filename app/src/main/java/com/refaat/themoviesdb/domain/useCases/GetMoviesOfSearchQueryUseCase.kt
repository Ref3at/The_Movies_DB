package com.refaat.themoviesdb.domain.useCases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesOfSearchQueryUseCase (private val repository: TheMovieDbRepository) {
    operator fun invoke(searchQuery:String): LiveData<PagingData<Movie>> {
        return repository.getMoviesOfSearchQuery(searchQuery)
    }

}
