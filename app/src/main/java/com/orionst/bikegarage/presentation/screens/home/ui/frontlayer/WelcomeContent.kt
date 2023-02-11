package com.orionst.bikegarage.presentation.screens.home.ui.frontlayer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.orionst.bikegarage.R

@Composable
fun WelcomeContent(
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