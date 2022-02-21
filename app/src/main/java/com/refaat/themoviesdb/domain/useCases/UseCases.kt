package com.refaat.themoviesdb.domain.useCases

import java.security.cert.CertPathValidatorSpi

data class UseCases(
    val getNowPlayingUseCase: GetNowPlayingUseCase,
    val getPopularUseCase: GetPopularUseCase,
    val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    val getUpComingUseCase: GetUpComingUseCase
)