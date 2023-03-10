package com.orionst.bikegarage.domain

import com.orionst.bikegarage.domain.entity.Bike
import kotlinx.coroutines.flow.Flow

interface BikeRepository {
    val allBikes: Flow<List<Bike>>

    suspend fun insert(bike: Bike)
    suspend fun update(bike: Bike)
    suspend fun getBike(bikeId: Int): Bike
    suspend fun deleteBike(bikeId: Int)
}
