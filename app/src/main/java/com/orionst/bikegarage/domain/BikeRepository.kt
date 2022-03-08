package com.orionst.bikegarage.domain

import com.orionst.bikegarage.domain.entity.Bike
import kotlinx.coroutines.flow.Flow

interface BikeRepository {
    val allBikes: Flow<List<Bike>>

    suspend fun insert(bike: Bike)
}