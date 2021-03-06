package com.orionst.bikegarage.domain

import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.domain.entity.BikeToSave
import kotlinx.coroutines.flow.Flow

interface BikeRepository {
    val allBikes: Flow<List<Bike>>

    suspend fun insert(bike: BikeToSave)
}