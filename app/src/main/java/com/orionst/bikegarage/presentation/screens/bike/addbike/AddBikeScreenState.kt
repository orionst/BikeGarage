package com.orionst.bikegarage.presentation.screens.bike.addbike

import com.orionst.bikegarage.domain.entity.Bike

sealed class AddBikeScreenState {

    data class Editing(val bike: Bike? = null) : AddBikeScreenState()

    object Processing : AddBikeScreenState()

    object Saved : AddBikeScreenState()
}


