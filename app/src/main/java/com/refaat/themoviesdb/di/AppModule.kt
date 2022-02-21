package com.refaat.themoviesdb.di

import android.app.Application
import androidx.room.Room
import com.refaat.themoviesdb.data.localSource.AppDataBase
import com.refaat.themoviesdb.data.remoteSource.CustomOkHttpClient
import com.refaat.themoviesdb.data.remoteSource.TheMovieDatabaseAPI
import com.refaat.themoviesdb.data.repository.TheMovieDbRepositoryImpl
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import com.refaat.themoviesdb.domain.useCases.*
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
    fun providesAppDataBase(app: Application): AppDataBase {
        return Room.databaseBuilder(app, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

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
        appDataBase: AppDataBase,
        api: TheMovieDatabaseAPI
    ): TheMovieDbRepository {
        return TheMovieDbRepositoryImpl(api, appDataBase.dao)
    }

    @Provides
    @Singleton
    fun providesTheUseCases(
        repository: TheMovieDbRepository
    ): UseCases {
        return UseCases(
            getNowPlayingUseCase = GetNowPlayingUseCase(repository),
            getPopularUseCase = GetPopularUseCase(repository),
            getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(repository),
            getUpComingUseCase = GetUpComingUseCase(repository),
            getMovieDetailUseCase = GetMovieDetailUseCase(repository),
            addMovieDetailToFavorites = AddMovieDetailToFavorites(repository),
            deleteMovieDetailFromFavorites = DeleteMovieDetailFromFavorites(repository)
        )
    }

}