package com.orionst.bikegarage.presentation.screens.addbike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.domain.entity.BikeToSave
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBikeViewModel @Inject constructor(
    private val bikeRepository: BikeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<AddBikeScreenState> =
        MutableStateFlow(AddBikeScreenState.Editing)
    val uiState = _uiState.asStateFlow()

    fun addBike(brand: String, model: String, description: String) {
        val bike = BikeToSave(
            brandName = brand,
            modelName = model,
            description = description
        )
        _uiState.update { AddBikeScreenState.Saving }
        viewModelScope.launch {
            bikeRepository.insert(bike)
            _uiState.update { AddBikeScreenState.Saved }
        }
    }
}
