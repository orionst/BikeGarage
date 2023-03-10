package com.orionst.bikegarage.domain.entity

import java.util.*

data class Bike(
    val uid: Int = 0,
    val brandName: String,
    val modelName: String,
    val creationDate: Date? = null,
    val description: String
)
