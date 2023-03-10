package com.orionst.bikegarage.data.room.daos

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionsDao {
    @Query("SELECT * FROM brands")
    fun getAllBrands(): Flow<List<String>>
}
