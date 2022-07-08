package com.orionst.bikegarage.data.room.converters

import androidx.room.TypeConverter
import com.orionst.bikegarage.domain.enums.ComponentType

class ComponentTypeConverter {
    @TypeConverter
    fun toComponentType(value: String) = enumValueOf<ComponentType>(value)

    @TypeConverter
    fun fromComponentType(value: ComponentType) = value.name
}
