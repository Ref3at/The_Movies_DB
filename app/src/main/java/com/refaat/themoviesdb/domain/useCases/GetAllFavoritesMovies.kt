package com.refaat.themoviesdb.domain.useCases

import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesMovies(private val repository: TheMovieDbRepository) {
    suspend operator fun invoke(): Flow<List<Movie>> {
        return repository.getAllFavoritesMovies()
    }
}