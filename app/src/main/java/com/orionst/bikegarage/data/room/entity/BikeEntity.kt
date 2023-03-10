package com.orionst.bikegarage.data.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "bikes",
    foreignKeys = [
        ForeignKey(
            entity = BrandEntity::class,
            parentColumns = arrayOf("name"),
            childColumns = arrayOf("brandName"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ],
    indices = [Index(
        name = "BikeIndice",
        value = ["brandName", "modelName"],
//        orders = [Index.Order.ASC, Index.Order.ASC]
        )],
)
data class BikeEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val brandName: String,
    val modelName: String,
    val creationDate: Date?,
    val description: String
)
