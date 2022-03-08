package com.orionst.bikegarage.ui

sealed class Screen(val route: String) {
    object BikeList : Screen("bikelist")
    object BikeDetails : Screen("bikedetails/{bike_id}") {
        fun createRoute(bikeId: String) = "bikedetails/$bikeId"
    }
}