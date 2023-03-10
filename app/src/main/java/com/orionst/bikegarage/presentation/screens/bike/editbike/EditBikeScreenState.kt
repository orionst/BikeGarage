package com.orionst.bikegarage.presentation.screens.bike.editbike

import com.orionst.bikegarage.domain.entity.Bike

sealed class EditBikeScreenState {

    data class Editing(val bike: Bike? = null) : EditBikeScreenState()

    object Processing : EditBikeScreenState()

    object Saved : EditBikeScreenState()
}


