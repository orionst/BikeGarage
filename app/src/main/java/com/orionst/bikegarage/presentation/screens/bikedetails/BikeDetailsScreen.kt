package com.orionst.bikegarage.presentation.screens.bikedetails

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.*
import com.orionst.bikegarage.R
import com.orionst.bikegarage.presentation.screens.bikedetails.components.BikeComponentsScreen
import com.orionst.bikegarage.presentation.screens.bikedetails.rides.BikeRidesScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun BikeDetailsScreen(
    bikeId: String,
    upPress: () -> Unit,
) {
    val tabs = listOf(
        BikeDetailsTabItem.Components(bikeId),
        BikeDetailsTabItem.Rides(bikeId),
    )
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.bike_details_title, bikeId))
                },
                navigationIcon = {
                    IconButton(onClick = upPress) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Tabs(tabs: List<BikeDetailsTabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
//        backgroundColor = Color.Black,
//        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "") },
                text = { Text(stringResource(id = tab.title)) },
                selected = (pagerState.currentPage == index),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<BikeDetailsTabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }
}

sealed class BikeDetailsTabItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    val screen: @Composable () -> Unit,
) {
    class Components(bikeId: String) : BikeDetailsTabItem(
        icon = R.drawable.ic_cog,
        title = R.string.tab_item_components,
        screen = { BikeComponentsScreen(bikeId) }
    )

    class Rides(bikeId: String) : BikeDetailsTabItem(
        icon = R.drawable.ic_bike,
        title = R.string.tab_item_rides,
        screen = { BikeRidesScreen(bikeId) }
    )
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview("Tabs")
@Preview("Tabs (night)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TabsPreview() {
    MaterialTheme {
        val tabs = listOf(
            BikeDetailsTabItem.Components("1"),
            BikeDetailsTabItem.Rides("1"),
        )
        val pagerState = rememberPagerState()

        Tabs(tabs = tabs, pagerState = pagerState)
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun TabsContentPreview() {
    MaterialTheme {
        val tabs = listOf(
            BikeDetailsTabItem.Components("1"),
            BikeDetailsTabItem.Rides("1"),
        )
        val pagerState = rememberPagerState()
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        BikeDetailsScreen("") {}
    }
}
