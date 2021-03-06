package com.orionst.bikegarage.presentation.mappers

import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.presentation.entity.BikeUi

fun List<Bike>.toUiModel(): List<BikeUi> = map { it.toUiModel() }

private fun Bike.toUiModel() = BikeUi(
    uid = uid,
    brandName = brandName,
    modelName = modelName,
    description = description
)
