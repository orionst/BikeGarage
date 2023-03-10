package com.orionst.bikegarage.presentation.screens.bike

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orionst.bikegarage.R
import com.orionst.bikegarage.presentation.theme.BikeGarageTheme
import com.orionst.bikegarage.presentation.ui.autocomplete.AutoCompleteOutlinedTextField

@Composable
fun AddEditBikeCommonScreen(
    uiState: AddEditBikeScreenState,
    upPress: () -> Unit,
    saveBike: (String, String, String) -> Unit,
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {
        TopAppBar(
            title = { Text(stringResource(id = R.string.add_bike_text)) },
            actions = {
                IconButton(onClick = upPress) {
                    Icon(Icons.Default.Close, contentDescription = null)
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            when (uiState) {
                is AddEditBikeScreenState.Editing -> {
                    Fields(
                        state = uiState,
                        addBikePress = { brand, model, description ->
                            saveBike(brand, model, description)
                        },
                        onCancel = upPress,
                    )
                }
                AddEditBikeScreenState.Saved -> upPress.invoke()
                AddEditBikeScreenState.Processing -> {
                    Loading()
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.Fields(
    state: AddEditBikeScreenState.Editing,
    addBikePress: (brand: String, model: String, description: String) -> Unit,
    onCancel: () -> Unit,
) {
    val prefilledBike = state.bike
    var brand by remember { mutableStateOf(prefilledBike?.brandName.orEmpty()) }
    var model by remember { mutableStateOf(prefilledBike?.modelName.orEmpty()) }
    var description by remember { mutableStateOf(prefilledBike?.description.orEmpty()) }

    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)

    AutoCompleteOutlinedTextField(
        modifier = fieldModifier,
        text = brand,
        options = state.brandList,
        onValueChange = { brand = it.trimStart() },
        label = { Text(text = stringResource(R.string.brand_field_label)) },
        singleLine = true
    )
    OutlinedTextField(
        modifier = fieldModifier,
        value = model,
        onValueChange = { model = it.trimStart() },
        label = { Text(text = stringResource(R.string.model_field_label)) },
        singleLine = true
    )
    OutlinedTextField(
        modifier = fieldModifier.height(100.dp),
        value = description,
        onValueChange = { description = it.trimStart() },
        label = { Text(text = stringResource(R.string.description_field_label)) },
        maxLines = 3
    )

    Spacer(modifier = Modifier.weight(1f))

    BottomButtons(
        addBikeEnabled = brand.isNotEmpty() && model.isNotEmpty(),
        addBikePress = { addBikePress.invoke(brand, model, description) },
        cancelPress = onCancel,
    )
}

@Composable
private fun BottomButtons(
    addBikeEnabled: Boolean,
    addBikePress: () -> Unit,
    cancelPress: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Button(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            onClick = cancelPress,
        ) {
            Text(text = stringResource(id = R.string.cancel_button_text))
        }

        Button(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            enabled = addBikeEnabled,
            onClick = addBikePress
        ) {
            Text(
                text = stringResource(id = R.string.add_bike_text),
                textAlign = TextAlign.Center
            )
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

// --- Previews Block ---

@Preview("Add Bike Screen", showBackground = true)
@Preview("Add Bike Screen (night)", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FieldsPreview() {
    BikeGarageTheme {
        Column {
            Fields(
                state = AddEditBikeScreenState.Editing(bike = null),
                addBikePress = { _, _, _ -> },
                onCancel = {},
            )
        }
    }
}
