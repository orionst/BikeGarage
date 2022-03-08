package com.orionst.bikegarage.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orionst.bikegarage.ui.screens.bikelist.BikeListScreen

@Composable
fun BikeGarageApp(
    appState: BikeGarageAppState = rememberBikeGarageAppSate()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.BikeList.route
    ) {
        composable(Screen.BikeList.route) {
            BikeListScreen()
        }
        composable(Screen.BikeDetails.route) {

        }
    }
}
