package com.orionst.bikegarage.presentation.screens.bikelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.presentation.mappers.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeListViewModel @Inject constructor(
    private val bikeRepository: BikeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<BikeListScreenState> =
        MutableStateFlow(BikeListScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            bikeRepository.allBikes
                .map { it.toUiModel() }
                .collect { bikes ->
                    _uiState.update {
                        if (bikes.isEmpty()) {
                            BikeListScreenState.EmptyGarage
                        } else {
                            BikeListScreenState.Garage(bikes)
                        }
                    }
                }
        }
    }
}
