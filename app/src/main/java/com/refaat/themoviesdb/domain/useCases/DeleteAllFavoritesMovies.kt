package com.refaat.themoviesdb.domain.useCases

import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository

class DeleteAllFavoritesMovies(private val repository: TheMovieDbRepository) {
    suspend operator fun invoke() {
        repository.deleteAllFavoritesMovies()
    }

}