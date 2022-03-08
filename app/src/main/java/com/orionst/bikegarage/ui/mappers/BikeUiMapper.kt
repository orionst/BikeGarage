package com.orionst.bikegarage.ui.mappers

import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.ui.entity.BikeUi

fun List<Bike>.toUiModel(): List<BikeUi> = map { it.toUiModel() }

private fun Bike.toUiModel() = BikeUi(
    brandName = brandName,
    modelName = modelName,
    description = description
)
