package com.orionst.bikegarage.ui.screens.bikelist

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orionst.bikegarage.R
import com.orionst.bikegarage.ui.entity.BikeUi
import com.orionst.bikegarage.ui.theme.BikeGarageTheme

@Composable
fun BikeListScreen(
    viewModel: BikeListViewModel = hiltViewModel()
) {
    val bikes by viewModel.bikes.collectAsState()

    if (bikes.isEmpty()) {
        WelcomeContent(
            modifier = Modifier.fillMaxSize(),
            onAddBikeClick = { viewModel.onAddBike() }
        )
    } else {
        BikeList(bikes = bikes)
    }
}

@Composable
private fun WelcomeContent(
    modifier: Modifier,
    onAddBikeClick: () -> Unit,
) {
    Column(
        modifier = modifier,
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

@Composable
private fun BikeList(bikes: List<BikeUi>) {
    LazyColumn {
        items(bikes) {
            Text(text = it.modelName)
        }
    }
}

@Preview("Welcome Content", showBackground = true)
@Preview("Welcome Content (night)", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WelcomeContentPreview() {
    BikeGarageTheme {
        WelcomeContent(
            modifier = Modifier.fillMaxWidth(),
            onAddBikeClick = {}
        )
    }
}
