package com.refaat.themoviesdb.di

import com.refaat.themoviesdb.data.remoteSource.CustomOkHttpClient
import com.refaat.themoviesdb.data.remoteSource.TheMovieDatabaseAPI
import com.refaat.themoviesdb.data.repository.TheMovieDbRepositoryImpl
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import com.refaat.themoviesdb.domain.useCases.GetNowPlayingUseCase
import com.refaat.themoviesdb.domain.useCases.GetPopularUseCase
import com.refaat.themoviesdb.domain.useCases.GetTopRatedMoviesUseCase
import com.refaat.themoviesdb.domain.useCases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): CustomOkHttpClient {
        return CustomOkHttpClient
    }

    @Provides
    @Singleton
    fun providesTheMovieDatabaseAPI(customOkHttpClient: CustomOkHttpClient): TheMovieDatabaseAPI {
        return Retrofit.Builder()
            .baseUrl(TheMovieDatabaseAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(customOkHttpClient.getCustomOkHttpClient())
            .build().create(TheMovieDatabaseAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesTheRepository(
        api: TheMovieDatabaseAPI
    ): TheMovieDbRepository {
        return TheMovieDbRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesTheUseCases(
        repository: TheMovieDbRepository
    ): UseCases {
        return UseCases(
            getNowPlayingUseCase = GetNowPlayingUseCase(repository),
            getPopularUseCase = GetPopularUseCase(repository),
            getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(repository)
        )
    }

}