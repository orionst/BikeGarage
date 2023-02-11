package com.orionst.bikegarage.presentation.screens.home.ui.backlayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.orionst.bikegarage.R
import com.orionst.bikegarage.presentation.entity.BikeUi
import com.orionst.bikegarage.presentation.screens.home.HomeScreenState

@Composable
fun BackLayerContent(
    uiState: HomeScreenState,
    onAddBike: () -> Unit,
    onSelectBikeItem: (BikeUi) -> Unit,
    onEditBike: (BikeUi) -> Unit,
    onDeleteBike: (BikeUi) -> Unit,
) {
    if (uiState is HomeScreenState.Garage) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            uiState.bikes.forEach { bike ->
                BikeListItem(
                    item = bike,
                    selected = (bike == uiState.selectedBike),
                    onItemClick = { onSelectBikeItem(bike) },
                    onEditItem = { onEditBike(bike) },
                    onDeleteItem = { onDeleteBike(bike) },
                )
            }
            Divider(color = MaterialTheme.colors.onPrimary)
            TextButton(onClick = onAddBike) {
                Text(
                    text = stringResource(id = R.string.add_bike_text),
                    color = MaterialTheme.colors.onPrimary,
                )
            }
        }
    } else {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.empty_bike_list_backlayer_text),
        )
    }
}

@Composable
private fun BikeListItem(
    item: BikeUi,
    selected: Boolean,
    onItemClick: () -> Unit,
    onEditItem: () -> Unit,
    onDeleteItem: () -> Unit,
) {
    Row {
        TextButton(onClick = onItemClick) {
            Column {
                Text(
                    text = item.fullName,
                    color = with(MaterialTheme.colors) {
                        if (selected) onPrimary.copy(alpha = 0.7f) else onPrimary
                    },
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold.takeIf { selected }
                )
                if (item.description.isNotBlank()) {
                    Text(
                        text = item.description,
                        color = with(MaterialTheme.colors) {
                            if (selected) onPrimary.copy(alpha = 0.7f) else onPrimary
                        },
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Bold.takeIf { selected }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onEditItem) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Bike Button")
        }
        IconButton(onClick = onDeleteItem) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Bike Button")
        }
    }
}
