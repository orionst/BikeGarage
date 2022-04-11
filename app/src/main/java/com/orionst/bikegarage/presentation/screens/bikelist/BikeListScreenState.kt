package com.orionst.bikegarage.presentation.screens.bikelist

import com.orionst.bikegarage.presentation.entity.BikeUi

sealed class BikeListScreenState {

    object Loading : BikeListScreenState()

    object EmptyGarage: BikeListScreenState()

    data class Garage(val bikes: List<BikeUi>) : BikeListScreenState()
}
