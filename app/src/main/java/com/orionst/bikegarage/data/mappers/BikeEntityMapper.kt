package com.orionst.bikegarage.data.mappers

import com.orionst.bikegarage.data.room.entity.BikeEntity
import com.orionst.bikegarage.domain.entity.Bike

fun List<BikeEntity>.toBikeList(): List<Bike> = map { it.toBike() }

private fun BikeEntity.toBike() = Bike(
    brandName = brandName,
    modelName = modelName,
    creationDate = creationDate,
    description = description
)

fun List<Bike>.toBikeEntityList(): List<BikeEntity> = map { it.toBikeEntity() }

fun Bike.toBikeEntity() = BikeEntity(
    brandName = brandName,
    modelName = modelName,
    creationDate = creationDate,
    description = description
)
