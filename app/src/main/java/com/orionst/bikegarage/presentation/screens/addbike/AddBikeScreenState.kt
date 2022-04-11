package com.orionst.bikegarage.presentation.screens.addbike

sealed class AddBikeScreenState {

    object Editing: AddBikeScreenState()

    object Saving : AddBikeScreenState()

    object Saved : AddBikeScreenState()
}


