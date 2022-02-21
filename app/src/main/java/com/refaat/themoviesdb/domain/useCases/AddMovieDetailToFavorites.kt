package com.refaat.themoviesdb.domain.useCases

import com.refaat.themoviesdb.domain.model.MovieDetail
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository

class AddMovieDetailToFavorites(val repository: TheMovieDbRepository) {
    suspend operator fun invoke(movieDetail: MovieDetail) {
        repository.addMovieToFavorites(movieDetail)
    }
}