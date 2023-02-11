package com.orionst.bikegarage.presentation.screens.addcomponent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.orionst.bikegarage.presentation.ScreenDeeplink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddEditComponentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val bikeId: Int = checkNotNull(
        savedStateHandle[ScreenDeeplink.BIKE_ID]
    )

    private val _uiState: MutableStateFlow<Int> = MutableStateFlow(bikeId)
    val uiState = _uiState.asStateFlow()
}
