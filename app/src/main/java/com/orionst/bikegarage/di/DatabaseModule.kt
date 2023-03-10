package com.orionst.bikegarage.di

import android.content.Context
import androidx.room.Room
import com.orionst.bikegarage.data.room.BikeGarageDatabase
import com.orionst.bikegarage.data.room.daos.BikeDao
import com.orionst.bikegarage.data.room.daos.CollectionsDao
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
            context = appContext,
            klass = BikeGarageDatabase::class.java,
            name = DATABASE_NAME
        )
            .createFromAsset("database/bike_garage_database.db")
//            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBikeDao(database: BikeGarageDatabase): BikeDao =
        database.bikeDao()

    @Provides
    @Singleton
    fun provideCollectionsDao(database: BikeGarageDatabase): CollectionsDao =
        database.collectionsDao()
}
