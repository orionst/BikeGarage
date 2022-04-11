package com.orionst.bikegarage.presentation

sealed class Screen(val route: String) {
    object BikeList : Screen("bikelist")
    object AddBike : Screen("addbike")
    object BikeDetails : Screen("bikedetails/{bike_id}") {
        fun createRoute(bikeId: String) = "bikedetails/$bikeId"
    }
}