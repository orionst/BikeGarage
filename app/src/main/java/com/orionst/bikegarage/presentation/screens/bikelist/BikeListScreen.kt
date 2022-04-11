package com.orionst.bikegarage.presentation.screens.bikelist

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orionst.bikegarage.R
import com.orionst.bikegarage.presentation.entity.BikeUi
import com.orionst.bikegarage.presentation.theme.BikeGarageTheme

@Composable
fun BikeListScreen(
    onAddBikeClick: () -> Unit,
    viewModel: BikeListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            if (uiState is BikeListScreenState.Garage) {
                FloatingActionButton(
                    onClick = { onAddBikeClick.invoke() }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add bike")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        when (uiState) {
            BikeListScreenState.Loading -> {
                Loading()
            }
            BikeListScreenState.EmptyGarage -> {
                WelcomeContent(
                    onAddBikeClick = onAddBikeClick
                )
            }
            is BikeListScreenState.Garage -> {
                BikeList(bikes = (uiState as BikeListScreenState.Garage).bikes)
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun WelcomeContent(
    onAddBikeClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(fraction = 0.8f),
            text = stringResource(R.string.empty_bike_list_text),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Button(onClick = onAddBikeClick) {
            Text(text = stringResource(id = R.string.add_new_bike_text))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BikeList(bikes: List<BikeUi>) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = bikes,
            key = { bike -> bike.uid }
        ) {
            BikeItem(
                modifier = Modifier.animateItemPlacement(),
                bike = it
            )
        }
    }
}

@Composable
private fun BikeItem(
    modifier: Modifier = Modifier,
    bike: BikeUi
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.primary)
            .padding(16.dp)
    ) {
        Text(
            text = bike.modelName,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h5
        )
        Text(
            text = bike.brandName,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = bike.description,
            color = MaterialTheme.colors.onPrimary,
            maxLines = 3
        )
    }
}

// --- Previews Block ---

@Preview("Welcome Content", showBackground = true)
@Preview("Welcome Content (night)", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WelcomeContentPreview() {
    BikeGarageTheme {
        WelcomeContent(
            onAddBikeClick = {}
        )
    }
}

@Preview("BikeList", showBackground = true)
@Preview("BikeList (night)", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BikeListPreview(
    @PreviewParameter(BikesUiProvider::class) bikes: List<BikeUi>
) {
    BikeGarageTheme {
        BikeList(bikes)
    }
}

internal class BikesUiProvider : PreviewParameterProvider<List<BikeUi>> {
    override val values: Sequence<List<BikeUi>> = sequenceOf(
        listOf(
            BikeUi(
                uid = 1,
                brandName = "Merida",
                modelName = "Ninety-Six RC XT",
                description = "My lovely full suspension cross-country bike."
            ),
            BikeUi(
                uid = 2,
                brandName = "Marin",
                modelName = "Palisades Trail",
                description = "The best of Marine's bikes"
            )
        )
    )
}
