package com.orionst.bikegarage.data.repository

import androidx.annotation.WorkerThread
import com.orionst.bikegarage.data.mappers.toBike
import com.orionst.bikegarage.data.mappers.toBikeEntity
import com.orionst.bikegarage.data.mappers.toBikeList
import com.orionst.bikegarage.data.room.daos.BikeDao
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.domain.entity.Bike
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
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

    @WorkerThread
    override suspend fun update(bike: Bike) {
        bikeDao.update(bike.toBikeEntity())
    }

    @WorkerThread
    override suspend fun getBike(bikeId: Int) =
        bikeDao.getBikeById(bikeId)
            .distinctUntilChanged()
            .first()
            .toBike()

    @WorkerThread
    override suspend fun deleteBike(bikeId: Int) {
        bikeDao.findAndDelete(bikeId)
    }
}
