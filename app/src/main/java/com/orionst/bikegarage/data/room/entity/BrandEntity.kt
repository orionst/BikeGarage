package com.orionst.bikegarage.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brands")
data class BrandEntity(
    @PrimaryKey
    val name: String
)
