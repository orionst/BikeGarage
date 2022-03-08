package com.orionst.bikegarage.domain.entity

import java.util.*

data class Bike(
    val brandName: String,
    val modelName: String,
    val creationDate: Date?,
    val description: String
)
