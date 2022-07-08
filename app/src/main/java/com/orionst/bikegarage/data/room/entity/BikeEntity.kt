package com.orionst.bikegarage.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bikes")
data class BikeEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val brandName: String,
    val modelName: String,
    val creationDate: Date?,
    val description: String
)
