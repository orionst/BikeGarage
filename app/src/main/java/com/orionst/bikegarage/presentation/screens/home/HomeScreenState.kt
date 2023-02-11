package com.orionst.bikegarage.presentation.screens.home

import com.orionst.bikegarage.presentation.entity.BikeUi
import com.orionst.bikegarage.presentation.screens.home.entity.BikeDetailsUi

sealed class HomeScreenState {

    object Loading : HomeScreenState()

    object EmptyGarage : HomeScreenState()

    data class Garage(
        val bikes: List<BikeUi>,
        val selectedBike: BikeUi,
        val selectedBikeDetails: BikeDetailsUi? = null,
    ) : HomeScreenState()
}
