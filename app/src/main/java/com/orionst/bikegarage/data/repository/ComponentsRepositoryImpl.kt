package com.orionst.bikegarage.data.repository

import com.orionst.bikegarage.data.room.BikeDao
import com.orionst.bikegarage.data.room.entity.BikeWithComponents
import com.orionst.bikegarage.domain.ComponentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComponentsRepositoryImpl @Inject constructor(
    private val bikeDao: BikeDao
): ComponentsRepository {
    override suspend fun getComponentsForBike(bikeId: Int): Flow<BikeWithComponents> {
        return bikeDao.getComponentsForBike(bikeId)
    }
}
