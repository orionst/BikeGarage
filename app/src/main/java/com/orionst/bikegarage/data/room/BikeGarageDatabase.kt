package com.orionst.bikegarage.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.orionst.bikegarage.data.room.converters.ComponentTypeConverter
import com.orionst.bikegarage.data.room.converters.DateConverter
import com.orionst.bikegarage.data.room.daos.BikeDao
import com.orionst.bikegarage.data.room.daos.CollectionsDao
import com.orionst.bikegarage.data.room.entity.BikeEntity
import com.orionst.bikegarage.data.room.entity.BrandEntity
import com.orionst.bikegarage.data.room.entity.ComponentEntity

@Database(
    entities = [
        BikeEntity::class,
        BrandEntity::class,
        ComponentEntity::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    DateConverter::class,
    ComponentTypeConverter::class,
)
abstract class BikeGarageDatabase : RoomDatabase() {
    abstract fun bikeDao(): BikeDao
    abstract fun collectionsDao(): CollectionsDao
}
