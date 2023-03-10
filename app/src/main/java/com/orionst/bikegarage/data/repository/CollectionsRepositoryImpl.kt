package com.orionst.bikegarage.data.repository

import com.orionst.bikegarage.data.room.daos.CollectionsDao
import com.orionst.bikegarage.domain.CollectionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionsRepositoryImpl @Inject constructor(
    collectionsDao: CollectionsDao,
) : CollectionsRepository {
    override val brands: Flow<List<String>> = collectionsDao.getAllBrands()
}
