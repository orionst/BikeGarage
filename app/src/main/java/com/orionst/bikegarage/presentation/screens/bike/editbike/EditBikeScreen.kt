package com.orionst.bikegarage.presentation.screens.bike.editbike

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.orionst.bikegarage.presentation.screens.bike.AddEditBikeCommonScreen

@Composable
fun EditBikeScreen(
    upPress: () -> Unit,
    viewModel: EditBikeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    AddEditBikeCommonScreen(
        uiState = uiState,
        upPress = upPress,
        saveBike = viewModel::updateBike
    )
}
