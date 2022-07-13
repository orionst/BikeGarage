package com.orionst.bikegarage.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orionst.bikegarage.presentation.screens.addbike.AddBikeScreen
import com.orionst.bikegarage.presentation.screens.bikedetails.BikeDetailsScreen
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
                onAddBikeClick = { actions.addBikePress(backStackEntry) },
                onBikeClick = { actions.onBikeClick(backStackEntry, it) }
            )
        }
        composable(Screen.AddBike.route) { backStackEntry: NavBackStackEntry ->
            AddBikeScreen(
                upPress = { actions.upPress(backStackEntry) }
            )
        }
        composable(
            route = Screen.BikeDetails.route,
            arguments = listOf(
                navArgument(ScreenDeeplink.BIKE_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry: NavBackStackEntry ->
//            val bikeId = backStackEntry.arguments?.getInt(ScreenDeeplink.BIKE_ID)
//
//            requireNotNull(bikeId)

            BikeDetailsScreen(
                upPress = { actions.upPress(backStackEntry) },
            )
        }
        composable(
            route = Screen.AddBikeComponent.route,
            arguments = listOf(
                navArgument(ScreenDeeplink.BIKE_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
//            AddBikeComponentScreen(
//                upPress = { actions.upPress(backStackEntry) }
//            )
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

    val addBikeComponentPress: (from: NavBackStackEntry) -> Unit = { from ->
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.AddBikeComponent.route)
        }
    }

    val onBikeClick: (from: NavBackStackEntry, bikeId: Int) -> Unit = { from, bikeId ->
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.BikeDetails.createRoute(bikeId = bikeId))
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
