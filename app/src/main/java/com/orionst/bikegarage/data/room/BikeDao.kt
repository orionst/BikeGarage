package com.orionst.bikegarage.data.room

import androidx.room.*
import com.orionst.bikegarage.data.room.entity.BikeEntity
import com.orionst.bikegarage.data.room.entity.BikeWithComponents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single

@Dao
interface BikeDao {
    @Query("SELECT * FROM bikes")
    fun getAllBikes(): Flow<List<BikeEntity>>

    @Transaction
    @Query("SELECT * FROM bikes where uid = :bikeId")
    fun getComponentsForBike(bikeId: Int): Flow<BikeWithComponents>

    @Query("SELECT * FROM bikes WHERE uid = :id")
    fun getBikeById(id: Int): Flow<BikeEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(bike: BikeEntity)

    @Update
    suspend fun update(vararg bikes: BikeEntity)

    @Delete
    suspend fun delete(bike: BikeEntity)

    @Transaction
    suspend fun findAndDelete(id: Int) {
        val entity = getBikeById(id)
        delete(entity.first())
    }
}
