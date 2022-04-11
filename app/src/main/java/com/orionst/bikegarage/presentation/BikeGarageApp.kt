package com.orionst.bikegarage.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orionst.bikegarage.presentation.screens.addbike.AddBikeScreen
import com.orionst.bikegarage.presentation.screens.bikelist.BikeListScreen

@Composable
fun BikeGarageApp(
    appState: BikeGarageAppState = rememberBikeGarageAppSate()
) {
    val actions = remember(appState.navController) { MainActions(appState.navController) }

    NavHost(
        navController = appState.navController,
        startDestination = Screen.BikeList.route
    ) {
        composable(Screen.BikeList.route) { backStackEntry: NavBackStackEntry ->
            BikeListScreen(
                onAddBikeClick = { actions.addBikePress(backStackEntry) }
            )
        }
        composable(Screen.AddBike.route) { backStackEntry: NavBackStackEntry ->
            AddBikeScreen(
                upPress = { actions.upPress(backStackEntry) }
            )
        }
        composable(Screen.BikeDetails.route) {

        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val onboardingComplete: () -> Unit = {
        navController.popBackStack()
    }

    val upPress: (from: NavBackStackEntry) -> Unit = { from ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigateUp()
        }
    }

    val addBikePress: (from: NavBackStackEntry) -> Unit = { from ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.AddBike.route)
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
