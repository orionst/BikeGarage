package com.orionst.bikegarage.presentation.screens.bike

import com.orionst.bikegarage.domain.entity.Bike

interface AddEditBikeScreenState {

    data class Editing(
        val bike: Bike? = null,
        val brandList: List<String> = emptyList(),
    ) : AddEditBikeScreenState

    object Processing : AddEditBikeScreenState

    object Saved : AddEditBikeScreenState
}
