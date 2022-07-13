package com.orionst.bikegarage.presentation.screens.bikedetails.components

import com.orionst.bikegarage.data.room.entity.BikeWithComponents
import com.orionst.bikegarage.presentation.entity.BikeComponentUi
import com.orionst.bikegarage.presentation.entity.BikeUi

sealed class BikeComponentsScreenState {
    object Loading: BikeComponentsScreenState()

    data class Content(
        val bike: BikeUi,
        val components: List<BikeComponentUi> = emptyList()
    ): BikeComponentsScreenState()
}

fun BikeWithComponents.toComponentsUi(): List<BikeComponentUi> =
    components.map {
        BikeComponentUi(
            type = it.type,
            name = it.name,
            notes = it.notes,
        )
    }

fun BikeComponentsScreenState.optionalContent() =
    this as? BikeComponentsScreenState.Content
