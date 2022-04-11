package com.orionst.bikegarage.domain.entity

import java.util.*

data class Bike(
    val uid: Int,
    val brandName: String,
    val modelName: String,
    val creationDate: Date? = null,
    val description: String
)

data class BikeToSave(
    val brandName: String,
    val modelName: String,
    val creationDate: Date? = null,
    val description: String
)
