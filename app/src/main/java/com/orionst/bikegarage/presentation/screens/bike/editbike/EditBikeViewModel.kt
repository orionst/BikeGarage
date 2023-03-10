package com.orionst.bikegarage.presentation.screens.bike.editbike

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.domain.CollectionsRepository
import com.orionst.bikegarage.presentation.ScreenDeeplink
import com.orionst.bikegarage.presentation.screens.bike.AddEditBikeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditBikeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bikeRepository: BikeRepository,
    private val collectionsRepository: CollectionsRepository,
) : ViewModel() {

    private val bikeId: Int = checkNotNull(savedStateHandle[ScreenDeeplink.BIKE_ID])

    private val _uiState: MutableStateFlow<AddEditBikeScreenState> =
        MutableStateFlow(AddEditBikeScreenState.Processing)
    val uiState = _uiState.asStateFlow()

    init {
        getBikeDetails(bikeId = bikeId)
    }

    private fun getBikeDetails(bikeId: Int) {
        viewModelScope.launch {
            val bike = async { bikeRepository.getBike(bikeId) }
            val brands = async { collectionsRepository.brands.first() }

            _uiState.update {
                AddEditBikeScreenState.Editing(
                    bike = bike.await(),
                    brandList = brands.await()
                )
            }
        }
    }

    fun updateBike(brand: String, model: String, description: String) {
        val state = uiState.value as? AddEditBikeScreenState.Editing
        state?.let {
            _uiState.update { AddEditBikeScreenState.Processing }
            state.bike?.copy(
                brandName = brand,
                modelName = model,
                description = description,
            )?.let { newBike ->
                viewModelScope.launch {
                    bikeRepository.update(newBike)
                    _uiState.update { AddEditBikeScreenState.Saved }
                }
            }
        }
    }
}
