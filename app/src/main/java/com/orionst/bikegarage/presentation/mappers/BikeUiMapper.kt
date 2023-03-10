package com.orionst.bikegarage.presentation.mappers

import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.presentation.entity.BikeUi

fun List<Bike>.toUiModel(): List<BikeUi> = map { it.toUiModel() }

fun Bike.toUiModel() = BikeUi(
    uid = uid,
    brandName = brandName,
    modelName = modelName,
    description = description
)

fun Bike.toDomainModel() = Bike(
    uid = uid,
    brandName = brandName,
    modelName = modelName,
    description = description
)
