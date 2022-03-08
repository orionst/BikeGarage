package com.orionst.bikegarage.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bikes")
data class BikeEntity(
    val brandName: String,
    val modelName: String,
    val creationDate: Date?,
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
