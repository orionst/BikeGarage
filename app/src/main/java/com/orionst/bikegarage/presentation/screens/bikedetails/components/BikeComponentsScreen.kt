package com.orionst.bikegarage.presentation.screens.bikedetails.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.orionst.bikegarage.presentation.entity.BikeComponentUi

@Composable
fun BikeComponentsScreen(
//    onAddComponentClick: () -> Unit,
    viewModel: BikeComponentsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}//onAddComponentClick
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add bike component")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        BikeComponentsList(
            contentPadding = paddingValues,
            components = uiState.optionalContent()?.components.orEmpty()
        )
    }

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colors.background)
//            .wrapContentSize(Alignment.Center)
//    ) {
//        val bikeNameText = uiState.optionalContent()?.let {
//            "${it.bike.brandName} ${it.bike.modelName}"
//        }.orEmpty()
//        Text(
//            text = bikeNameText.trim(),
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            fontSize = 25.sp
//        )
//    }
}

@Composable
private fun BikeComponentsList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    components: List<BikeComponentUi>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding =  contentPadding,
    ) {
        items(components) {

        }
    }
}
