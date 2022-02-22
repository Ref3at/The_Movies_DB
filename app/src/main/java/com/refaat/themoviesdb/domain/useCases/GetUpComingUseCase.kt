package com.refaat.themoviesdb.domain.useCases

import androidx.paging.PagingData
import androidx.paging.map
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class GetUpComingUseCase(private val repository: TheMovieDbRepository) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMoviesUpcoming().map { pagingData ->
            pagingData.map { movie ->
                val genreListString: ArrayList<String> = arrayListOf()
                movie.genreIds?.forEach {
                    val name = repository.getGenreNameById(it)
                    genreListString.add(name)
                }
                movie.genreString = genreListString.joinToString()
                movie
            }
        }
    }

}
