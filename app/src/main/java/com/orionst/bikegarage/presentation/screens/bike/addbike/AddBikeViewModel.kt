package com.orionst.bikegarage.presentation.screens.bike.addbike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.domain.CollectionsRepository
import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.presentation.screens.bike.AddEditBikeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBikeViewModel @Inject constructor(
    private val bikeRepository: BikeRepository,
    private val collectionsRepository: CollectionsRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AddEditBikeScreenState> =
        MutableStateFlow(AddEditBikeScreenState.Editing())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                AddEditBikeScreenState.Editing(
                    brandList = collectionsRepository.brands.first(),
                )
            }
        }
    }

    fun addBike(brand: String, model: String, description: String) {
        val bike = Bike(
            brandName = brand,
            modelName = model,
            description = description
        )
        _uiState.update { AddEditBikeScreenState.Processing }
        viewModelScope.launch {
            bikeRepository.insert(bike)
            _uiState.update { AddEditBikeScreenState.Saved }
        }
    }
}
