package com.orionst.bikegarage.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orionst.bikegarage.domain.enums.ComponentType

@Entity(tableName = "components")
data class ComponentEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val type: ComponentType,
    val name: String,
    val notes: String,
    val bikeId: Int,
)
