package com.orionst.bikegarage.data.room.entity

import androidx.room.*
import com.orionst.bikegarage.domain.enums.ComponentType

@Entity(
    tableName = "components",
    foreignKeys = [
        ForeignKey(
            entity = BikeEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("bikeId"),
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = BrandEntity::class,
            parentColumns = arrayOf("name"),
            childColumns = arrayOf("brand"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ],
//    indices = [Index(value = ["bikeId"]), Index(value = ["brand"])],
)
data class ComponentEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val type: ComponentType,
    val brand: String,
    val name: String,
    @ColumnInfo(name = "weight")
    val weighInGram: Float,
    val notes: String,
    val bikeId: Int,
)
