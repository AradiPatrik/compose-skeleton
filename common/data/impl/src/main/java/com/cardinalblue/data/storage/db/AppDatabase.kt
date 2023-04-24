package com.cardinalblue.data.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cardinalblue.data.storage.converter.Converters
import com.cardinalblue.data.storage.dao.MovieRemoteKeysDao
import com.cardinalblue.data.storage.dao.MoviesDao
import com.cardinalblue.data.storage.dao.ReviewRemoteKeysDao
import com.cardinalblue.data.storage.dao.ReviewsDao
import com.cardinalblue.data.storage.entity.MovieEntity
import com.cardinalblue.data.storage.entity.MovieRemoteKeyEntity
import com.cardinalblue.data.storage.entity.ReviewEntity
import com.cardinalblue.data.storage.entity.ReviewRemoteKeysEntity

@Database(
    entities = [
        MovieEntity::class,
        ReviewEntity::class,
        MovieRemoteKeyEntity::class,
        ReviewRemoteKeysEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao

    abstract fun reviewsDao(): ReviewsDao

    abstract fun reviewRemoteKeysDao(): ReviewRemoteKeysDao

    companion object {
        private const val DATABASE_NAME = "app_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context)
                    .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}