package com.orionst.bikegarage.data.mappers

import com.orionst.bikegarage.data.room.entity.BikeEntity
import com.orionst.bikegarage.domain.entity.Bike

fun List<BikeEntity>.toBikeList(): List<Bike> = map { it.toBike() }

fun BikeEntity.toBike() = Bike(
    uid = uid,
    brandName = brandName,
    modelName = modelName,
    creationDate = creationDate,
    description = description
)

fun Bike.toBikeEntity() = BikeEntity(
    uid = uid,
    brandName = brandName,
    modelName = modelName,
    creationDate = creationDate,
    description = description
)
