package com.orionst.bikegarage.presentation

sealed class Screen(val route: String) {
    object BikeList : Screen("bikelist")
    object AddBike : Screen("addbike")
    object BikeDetails : Screen("bikedetails/{${ScreenDeeplink.BIKE_ID}}") {
        fun createRoute(bikeId: Int) = "bikedetails/$bikeId"
    }
    object AddBikeComponent: Screen("bikecomponents/{${ScreenDeeplink.BIKE_ID}}") {
        fun createRoute(bikeId: Int) = "bikecomponents/$bikeId"
    }
    object EditBike: Screen("editbike/{${ScreenDeeplink.BIKE_ID}}") {
        fun createRoute(bikeId: Int) = "editbike/$bikeId"
    }
}

object ScreenDeeplink {
    const val BIKE_ID = "bike_id"
}