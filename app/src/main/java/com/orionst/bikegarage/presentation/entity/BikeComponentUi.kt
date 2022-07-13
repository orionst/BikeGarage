package com.orionst.bikegarage.presentation.entity

import com.orionst.bikegarage.domain.enums.ComponentType

data class BikeComponentUi(
    val type: ComponentType,
    val name: String,
    val notes: String,
)
