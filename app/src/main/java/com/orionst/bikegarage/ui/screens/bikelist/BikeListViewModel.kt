package com.orionst.bikegarage.ui.screens.bikelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orionst.bikegarage.domain.BikeRepository
import com.orionst.bikegarage.domain.entity.Bike
import com.orionst.bikegarage.ui.entity.BikeUi
import com.orionst.bikegarage.ui.mappers.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeListViewModel @Inject constructor(
    private val bikeRepository: BikeRepository
) : ViewModel() {

    private val _bikes = MutableStateFlow(emptyList<BikeUi>())
    val bikes = _bikes.asStateFlow()

    init {
        viewModelScope.launch {
            _bikes.emitAll(
                bikeRepository.allBikes.map { it.toUiModel() }
            )
        }
    }

    fun onAddBike() {
        val bike = Bike(
            brandName = "dasda",
            modelName = "dasd",
            creationDate = null,
            description = "ajshdna"
        )
        viewModelScope.launch {
            bikeRepository.insert(bike)
        }
    }
}
