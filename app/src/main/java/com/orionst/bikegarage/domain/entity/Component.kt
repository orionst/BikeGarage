package com.orionst.bikegarage.domain.entity

import com.orionst.bikegarage.domain.enums.ComponentType

data class Component(
    val type: ComponentType,
    val name: String,
    val notes: String = "",
)
