package com.orionst.bikegarage.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.orionst.bikegarage.data.room.converters.DateConverter
import com.orionst.bikegarage.data.room.entity.BikeEntity

@Database(entities = [BikeEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class BikeGarageDatabase: RoomDatabase() {
    abstract fun bikeDao(): BikeDao
}
