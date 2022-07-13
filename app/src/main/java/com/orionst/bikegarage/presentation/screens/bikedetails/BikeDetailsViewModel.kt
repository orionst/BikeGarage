package com.orionst.bikegarage.presentation.screens.bikedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.presentation.ScreenDeeplink
import com.orionst.bikegarage.presentation.entity.BikeUi
import com.orionst.bikegarage.presentation.mappers.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bikeRepository: BikeRepository,
) : ViewModel() {

    private val bikeId: Int = checkNotNull(
        savedStateHandle[ScreenDeeplink.BIKE_ID]
    )

    private val _uiState: MutableStateFlow<BikeUi?> = MutableStateFlow(null)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val bike = bikeRepository.getBike(bikeId)
            _uiState.update { bike.toUiModel() }
        }
    }
}
