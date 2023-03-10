package com.orionst.bikegarage.domain

import kotlinx.coroutines.flow.Flow

interface CollectionsRepository {
    val brands: Flow<List<String>>
}
