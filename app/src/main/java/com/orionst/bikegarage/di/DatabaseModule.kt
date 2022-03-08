package com.orionst.bikegarage.di

import android.content.Context
import androidx.room.Room
import com.orionst.bikegarage.data.room.BikeDao
import com.orionst.bikegarage.data.room.BikeGarageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "bike_garage_database"

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BikeGarageDatabase {
        return Room.databaseBuilder(
            appContext,
            BikeGarageDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideBikeDao(database: BikeGarageDatabase): BikeDao {
        return database.bikeDao()
    }
}
