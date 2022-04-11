package com.orionst.bikegarage.data.mappers

import com.orionst.bikegarage.data.room.entity.BikeEntity
import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.domain.entity.BikeToSave

fun List<BikeEntity>.toBikeList(): List<Bike> = map { it.toBike() }

private fun BikeEntity.toBike() = Bike(
    uid = uid,
    brandName = brandName,
    modelName = modelName,
    creationDate = creationDate,
    description = description
)

fun List<BikeToSave>.toBikeEntityList(): List<BikeEntity> = map { it.toBikeEntity() }

fun BikeToSave.toBikeEntity() = BikeEntity(
    brandName = brandName,
    modelName = modelName,
    creationDate = creationDate,
    description = description
)
