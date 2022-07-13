package com.orionst.bikegarage.presentation.screens.bikedetails.components

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orionst.bikegarage.domain.ComponentsRepository
import com.orionst.bikegarage.presentation.ScreenDeeplink
import com.orionst.bikegarage.presentation.mappers.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeComponentsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val componentsRepository: ComponentsRepository,
) : ViewModel() {
    private val bikeId: Int = checkNotNull(
        savedStateHandle[ScreenDeeplink.BIKE_ID]
    )

    private val _uiState: MutableStateFlow<BikeComponentsScreenState> =
        MutableStateFlow(BikeComponentsScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            componentsRepository.getComponentsForBike(bikeId)
                .collect { bikeWithComponents ->
                    _uiState.update {
                        BikeComponentsScreenState.Content(
                            bike = bikeWithComponents.bike.toUiModel(),
                            components = bikeWithComponents.toComponentsUi(),
                        )
                    }
                }

        }
    }
}