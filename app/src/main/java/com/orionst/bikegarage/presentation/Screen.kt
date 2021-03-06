package com.orionst.bikegarage.presentation

sealed class Screen(val route: String) {
    object BikeList : Screen("bikelist")
    object AddBike : Screen("addbike")
    object BikeDetails : Screen("bikedetails/{${ScreenDeeplink.BIKE_ID}}") {
        fun createRoute(bikeId: Int) = "bikedetails/$bikeId"
    }
}

object ScreenDeeplink {
    const val BIKE_ID = "bike_id"
}