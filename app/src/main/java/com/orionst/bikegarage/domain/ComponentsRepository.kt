package com.orionst.bikegarage.domain

import com.orionst.bikegarage.data.room.entity.BikeWithComponents
import kotlinx.coroutines.flow.Flow

interface ComponentsRepository {
    suspend fun getComponentsForBike(bikeId: Int): Flow<BikeWithComponents>
}
