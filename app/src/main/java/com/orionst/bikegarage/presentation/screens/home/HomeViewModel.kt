package com.orionst.bikegarage.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.presentation.entity.BikeUi
import com.orionst.bikegarage.presentation.mappers.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bikeRepository: BikeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            bikeRepository.allBikes
                .map { it.toUiModel() }
                .collect { bikes ->
                    _uiState.update {
                        if (bikes.isEmpty()) {
                            HomeScreenState.EmptyGarage
                        } else {
                            if (it is HomeScreenState.Garage) {
                                if (bikes.contains(it.selectedBike).not()) {
                                    it.copy(
                                        bikes = bikes,
                                        selectedBike = bikes.first()
                                    )
                                } else {
                                    it.copy(bikes = bikes)
                                }
                            } else {
                                HomeScreenState.Garage(
                                    bikes = bikes,
                                    selectedBike = bikes.first(),
                                )
                            }
                        }
                    }
                }
        }
    }

    fun onBikeListItemClick(item: BikeUi) {
        _uiState.update {
            if (it is HomeScreenState.Garage) it.copy(selectedBike = item) else it
        }
    }

    fun onDeleteBikeClick(bike: BikeUi) {
        viewModelScope.launch {
            bikeRepository.deleteBike(bike.uid)
        }
    }
}
