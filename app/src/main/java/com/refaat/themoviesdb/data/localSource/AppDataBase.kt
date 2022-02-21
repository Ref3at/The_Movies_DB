package com.refaat.themoviesdb.data.localSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.refaat.themoviesdb.data.TheMoviesDao
import com.refaat.themoviesdb.domain.model.MovieDetail

@Database(
    entities = [MovieDetail::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract val dao: TheMoviesDao

    companion object {
        const val DATABASE_NAME = "THE_MOVIES_APP_DB"
    }
}