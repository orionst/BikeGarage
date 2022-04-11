package com.orionst.bikegarage.presentation.screens.addbike

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orionst.bikegarage.R
import com.orionst.bikegarage.presentation.theme.BikeGarageTheme

@Composable
fun AddBikeScreen(
    upPress: () -> Unit,
    viewModel: AddBikeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            when (uiState) {
                AddBikeScreenState.Editing -> item {
                    Fields { brand, model, description ->
                        viewModel.addBike(brand, model, description)
                    }
                }
                AddBikeScreenState.Saved -> upPress.invoke()
                AddBikeScreenState.Saving -> item {
                    Loading()
                }
            }
        }
    }
}

@Composable
private fun Fields(
    addBikePress: (brand: String, model: String, description: String) -> Unit,
) {
    var brand by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)

    OutlinedTextField(
        modifier = fieldModifier,
        value = brand,
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
    Spacer(modifier = Modifier.size(8.dp))
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Button(
            enabled = (brand.isNotEmpty() && model.isNotEmpty()),
            onClick = {
                addBikePress.invoke(brand, model, description)
            }
        ) {
            Text(text = stringResource(id = R.string.add_bike_text))
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
        Fields(addBikePress = { _, _, _ -> })
    }
}
