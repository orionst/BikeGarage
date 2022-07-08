package com.orionst.bikegarage.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.domain.entity.Component

data class BikeWithComponents(
    @Embedded
    val bike: Bike,

    @Relation(
        parentColumn = "uid",
        entityColumn = "bikeId"
    )
    val components: List<ComponentEntity>,
)
