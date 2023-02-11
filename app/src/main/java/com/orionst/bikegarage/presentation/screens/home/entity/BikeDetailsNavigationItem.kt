package com.orionst.bikegarage.presentation.screens.home.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orionst.bikegarage.R

sealed class BikeDetailsNavigationItem(
    @DrawableRes val icon: Int,
    @StringRes val titleResId: Int,
    @StringRes val fabTextResId: Int,
) {
    object Components : BikeDetailsNavigationItem(
        icon = R.drawable.ic_cog,
        titleResId = R.string.tab_item_components,
        fabTextResId = R.string.fab_components_text,
    )

    object Rides : BikeDetailsNavigationItem(
        icon = R.drawable.ic_bike,
        titleResId = R.string.tab_item_rides,
        fabTextResId = R.string.fab_rides_text,
    )
}
