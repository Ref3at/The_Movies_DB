package com.refaat.themoviesdb.domain.useCases

import com.refaat.themoviesdb.domain.model.MovieDetail
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository

class DeleteMovieDetailFromFavorites(val repository: TheMovieDbRepository) {
    suspend operator fun invoke(movieDetail: MovieDetail) {
        repository.deleteMovieFromFavorites(movieDetail)
    }
}