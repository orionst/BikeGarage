package com.orionst.bikegarage.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.orionst.bikegarage.data.room.converters.ComponentTypeConverter
import com.orionst.bikegarage.data.room.converters.DateConverter
import com.orionst.bikegarage.data.room.entity.BikeEntity
import com.orionst.bikegarage.data.room.entity.ComponentEntity

@Database(
    entities = [BikeEntity::class, ComponentEntity::class],
    version = 1
)
@TypeConverters(
    DateConverter::class,
    ComponentTypeConverter::class,
)
abstract class BikeGarageDatabase: RoomDatabase() {
    abstract fun bikeDao(): BikeDao
}
