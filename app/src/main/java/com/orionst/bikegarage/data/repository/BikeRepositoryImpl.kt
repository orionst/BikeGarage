package com.orionst.bikegarage.data.repository

import androidx.annotation.WorkerThread
import com.orionst.bikegarage.data.mappers.toBikeEntity
import com.orionst.bikegarage.data.mappers.toBikeList
import com.orionst.bikegarage.data.room.BikeDao
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.domain.entity.Bike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BikeRepositoryImpl @Inject constructor(
    private val bikeDao: BikeDao
) : BikeRepository {

    override val allBikes: Flow<List<Bike>> = bikeDao.getAllBikes()
        .map { it.toBikeList() }

    @WorkerThread
    override suspend fun insert(bike: Bike) {
        bikeDao.insert(bike.toBikeEntity())
    }
}
